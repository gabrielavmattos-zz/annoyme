package com.example.root.annoyme;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private int tipo;
    private PendingIntent pendingIntent;
    private Double latitude, longitude;
    private Dados dados;
    private Button button;
    private ArrayList<String> respondidos, pendentes;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.button_main);
        dados = new Dados();
        setTodosAlarmes();

         if (dados.ArmazenamentoDisponivel(this)) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                System.out.println("Permissão dada");
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    System.out.println("Deve explicar o racionale");
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed, we can request the permission.
                    System.out.println("Deve só pedir permissão");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    // 1 is an app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                System.out.println("Permissão dada");
            }
        }
        ArrayList<String> list = dados.verificarArquivoPendentes(0, null);
        pendentes =  new ArrayList<String>();
        respondidos = new ArrayList<String>();
        String linha;
        if(list != null)
        {

            for(int i=0; i < list.size(); i++)
            {
                linha = list.get(i);

              //  System.out.println(linha);

                if(linha.contains("pendente"))
                {

                    pendentes.add(linha);
                   System.out.println("pendente");


                }
                else
                {
                    respondidos.add(linha);
                }

            }

        }

        if(pendentes.size()>0)
        {

            TextView text = (TextView) findViewById(R.id.mainActivity_label);
            text.setText("Você tem "+ pendentes.size()+" pendentes.");

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


    } //end onCreate

    private void setTodosAlarmes()
    {

        int id = 0;
        int numeroInterrupcoes = 10;
        Calendar calendar = Calendar.getInstance();
        Calendar[] calendarInt = new Calendar[numeroInterrupcoes];


        //Horário alarme verificação de pendencia
        // Horário alarme coleta demografica
        calendar.set(Calendar.HOUR, 20);
        calendar.set(Calendar.MINUTE, 00);
        tipo = 0; // tipo de notificação: coleta demografica
        setAlarme(calendar, tipo, id);

        id++;

        // Horário alarme coleta demografica
        calendar.clear();
        calendar.set(2016, 0, 23, 02, 20);
        tipo = 1; // tipo de notificação: coleta demografica
        setAlarme(calendar, tipo, id);

        id++;
//
        // Horário alarme coleta de personalização
        calendar.clear();
        calendar.set(2016, 0, 23, 02, 33);
        tipo = 2; // tipo de notificação: coleta pesonalizacao
        setAlarme(calendar, tipo, id);
        //  setAlarme(calendar, tipo, id);


        for(int i = 0; i < calendarInt.length; i++)
        {

           calendarInt[i] = Calendar.getInstance();
        }



        calendarInt[0].set(2016, 0, 23, 03,19);
        calendarInt[1].set(2016, 0, 23, 03,20);
        calendarInt[2].set(2016, 0, 23, 03,21);
        calendarInt[3].set(2016, 0, 23, 03,22);


        id++;

        // Interrupções

        tipo = 3; // tipo de notificação: interrupções

        for(int i = 0; i < calendarInt.length; i++)
        {

            setAlarme(calendarInt[i], tipo, i+2);
        }

        for(int i = 0; i < calendarInt.length; i++)
        {

            System.out.println(calendarInt[i].getTime());
        }
        //calendar.clear();
        // calendar.set(2016, 0, 22, 01, 44,59);
        //calendar.set(Calendar.SECOND, 30);

    }

    private void setAlarme(Calendar calendar, int tipo, int id) {

        System.out.println("Hora atual: "+Calendar.getInstance().getTime());
        System.out.println(tipo+" - Alarme: "+calendar.getTime());

        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm");
        String date = simpleFormat.format(calendar.getTimeInMillis());

        ArrayList<String> listaRespostas = new ArrayList<String>();
        listaRespostas.add(date);

        Intent myIntent = new Intent(MainActivity.this, MyBroadcastReceiver.class);
        myIntent.putExtra("tipo", tipo);
       // myIntent.putExtra("id_cenario", idCenario);
        myIntent.putExtra("respostas", listaRespostas);


        if (tipo == 3)
        {

            MyLocationListener gps = new MyLocationListener(MainActivity.this);
            if (gps.canGetLocation()) {
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();
                System.out.println(date + " - " + latitude + " - " + longitude);
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
         if (calendar.getTimeInMillis() - System.currentTimeMillis() > 0) {

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        //finish();
        ;
      }
        else{


            System.out.println("errorrrrrrrrrrrrrrrr");
        }

    }
}