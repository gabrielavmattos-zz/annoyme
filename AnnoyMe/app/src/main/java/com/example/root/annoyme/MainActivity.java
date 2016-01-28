package com.example.root.annoyme;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private static final int REQUEST_PERMISSIONS = 1;

    private int tipo;
    private PendingIntent pendingIntent;
    private Double latitude, longitude;
    private Dados dados;
    private AlertDialog.Builder alert;
    private Button button;
    private ArrayList<String> respondidos, pendentes;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String androidOS = Build.VERSION.RELEASE;
        context = this;

        Toast.makeText(getApplicationContext(), "Version: " + androidOS, Toast.LENGTH_LONG).show();

        button = (Button) findViewById(R.id.button_main);
        dados = new Dados();

        if(androidOS.contains("6."))
        {

            if (dados.ArmazenamentoDisponivel(this)) {
                requestMultiplePermissions(this);
            }
        }
        else
        {
            logicaGeral();
        }



    } //end onCreate

    private void setTodosAlarmes() {
        int id = 0;
        int numeroInterrupcoes = 10;
        boolean okDemo = false, okPerso = false;
        Calendar calendar = Calendar.getInstance();
        Calendar[] calendarInt = new Calendar[numeroInterrupcoes];

        //Horário alarme verificação de pendencia
        calendar.set(Calendar.HOUR, 20);
        calendar.set(Calendar.MINUTE, 00);
        tipo = 0; // tipo de notificação: verificação de pendência
        setAlarme(calendar, tipo, id);

        id++;

        // Horário alarme coleta demografica
        calendar.clear();
        calendar.set(2016, 0, 24, 23, 52);
        tipo = 1; // tipo de notificação: coleta demografica
        setAlarme(calendar, tipo, id);

        id++;

        if (dados.verificarEtapa("coletaDemo")) {


            // Horário alarme coleta de personalização
            calendar.clear();
            calendar.set(2016, 0, 24, 23, 55);
            tipo = 2; // tipo de notificação: coleta pesonalizacao
            setAlarme(calendar, tipo, id);
            //  setAlarme(calendar, tipo, id);

            if(dados.verificarEtapa("coletaPerso"))
            {


                for (int i = 0; i < calendarInt.length; i++) {
                    calendarInt[i] = Calendar.getInstance();
                }

                calendarInt[0].set(2016, 0, 24, 23, 54);
                calendarInt[1].set(2016, 0, 24, 23, 55);
                calendarInt[2].set(2016, 0, 24, 23, 56);
                calendarInt[3].set(2016, 0, 24, 23, 57);
                calendarInt[4].set(2016, 0, 24, 23, 58);
                calendarInt[5].set(2016, 0, 24, 23, 59);
                calendarInt[6].set(2016, 0, 24, 23, 54);

                id++;

                // Interrupções
                tipo = 3; // tipo de notificação: interrupções
                for (int i = 0; i < calendarInt.length; i++) {
                    setAlarme(calendarInt[i], tipo, i + 2);
                }

                for (int i = 0; i < calendarInt.length; i++) {
                    //System.out.println(calendarInt[i].getTime());
                }
            }
            else
                pendente(tipo, "questionário de coleta de personalização");

        }
        else
        {
            pendente(tipo, "questionário de coleta demográfica");


        }
    }

    private void setAlarme(Calendar calendar, int tipo, int id) {
        System.out.println("Hora atual: " + Calendar.getInstance().getTime());
        System.out.println(tipo + " - Alarme: " + calendar.getTime());

        ArrayList<String> listaRespostas = new ArrayList<String>();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm");
        String date = simpleFormat.format(calendar.getTimeInMillis());
        Intent myIntent = new Intent(MainActivity.this, MyBroadcastReceiver.class);

        listaRespostas.add(date);
        myIntent.putExtra("tipo", tipo);
        // myIntent.putExtra("id_cenario", idCenario);
        myIntent.putExtra("respostas", listaRespostas);


        if (calendar.getTimeInMillis() - System.currentTimeMillis() > 0) {

            if (tipo == 3) {
                MyLocationListener gps = new MyLocationListener(MainActivity.this);
                if (gps.canGetLocation()) {
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    myIntent.putExtra("latitude", latitude);
                    myIntent.putExtra("longitude", longitude);
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    latitude = null;
                    longitude = null;
                    // Can't get location.
                    // GPS or network is not enabled.
                    // Ask user to enable GPS/network in settings.
                    gps.showSettingsAlert();
                }
            }

            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, id, myIntent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            //finish();
        }
        else{
            System.out.println("ERROR: MainActivity.setAlarme.");
        }
    }

    private void logicaGeral()
    {

        setTodosAlarmes();

        System.out.println("(MainActivity.onRequestPermissionsResult: WRITE_EXTERNAL_STORAGE) OK");
        dados.criarDiretorio();
        ArrayList<String> list = dados.verificarArquivoPendentes(0, null);
        pendentes = new ArrayList<String>();
        respondidos = new ArrayList<String>();

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                String linha = list.get(i);
                //  //System.out.println(linha);
                if (linha.contains("pendente")) {
                    pendentes.add(linha);
                    System.out.println("(MainActivity.onRequestPermissionsResult: WRITE_EXTERNAL_STORAGE) linha pendente");
                } else {
                    respondidos.add(linha);
                }
            }
        }

        if (pendentes.size() > 0) {
            TextView text = (TextView) findViewById(R.id.mainActivity_label);
            text.setText("Você tem " + pendentes.size() + " pendentes.");

            button.setVisibility(View.VISIBLE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MainActivity.this, AgoraNao1.class);
                nextActivity.putExtra("respostas", pendentes.get(0));
                nextActivity.putStringArrayListExtra("respondidas", respondidos);
                startActivity(nextActivity);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    System.out.println("(MainActivity.onRequestPermissionsResult: WRITE_EXTERNAL_STORAGE) OK");

                    logicaGeral();
                } else {
                    System.out.println("(MainActivity.onRequestPermissionsResult: REQUEST_PERMISSIONS) Not Granted");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void pendente(final int tipo1, String label) {


        alert= new AlertDialog.Builder(context);
        alert.setTitle("Continuar estudo");
        alert.setMessage("Para continuar o estudo, você deve responder: "+label+".").setCancelable(false).setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        ArrayList<String> listaRespostas = new ArrayList<String>();
                        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm");
                        String date = simpleFormat.format(Calendar.getInstance().getTimeInMillis());
                        listaRespostas.add(date);

                        if(tipo1 == 1)
                        {
                            Intent coletademo = new Intent(MainActivity.this, ColetaDemo1.class);
                            coletademo.putExtra("respostas", listaRespostas);
                            startActivity(coletademo);
                            finish();
                        }
                        else
                        {

                            Intent coletademo = new Intent(MainActivity.this, ColetaPerso1.class);
                            coletademo.putExtra("respostas", listaRespostas);
                            startActivity(coletademo);
                            finish();
                        }

                    }
                });
        alert.create().show();

    }
    private void requestMultiplePermissions(Activity activity) {
        String locationPermission = Manifest.permission.ACCESS_FINE_LOCATION;
        String storagePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int hasLocPermission = ActivityCompat.checkSelfPermission(activity, locationPermission);
        int hasStorePermission = ActivityCompat.checkSelfPermission(activity, storagePermission);
        List<String> permissions = new ArrayList<String>();

        if (hasLocPermission != PackageManager.PERMISSION_GRANTED) {
            permissions.add(locationPermission);
        }
        if (hasStorePermission != PackageManager.PERMISSION_GRANTED) {
            permissions.add(storagePermission);
        }
        if (!permissions.isEmpty()) {
            String[] params = permissions.toArray(new String[permissions.size()]);
            ActivityCompat.requestPermissions(activity, params, REQUEST_PERMISSIONS);
        } else {
            // We already have permission, so handle as normal
        }
    }
}