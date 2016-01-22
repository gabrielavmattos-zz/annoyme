package com.example.root.annoyme;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends Activity {
    private int tipo;
    private PendingIntent pendingIntent;
    private Double latitude, longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int id = 0; //usado para setar vários alarmes, já que é necessário um pendingIntent  para cada alarme

        Calendar calendar = Calendar.getInstance();
        // Coleta Demografica
//
        tipo = 1; // tipo de notificação: coleta demografica
//        calendar.clear();
//        calendar.set(2016, 0, 21, 17, 11);
        //  setAlarme(calendar, tipo, id);
//
        id++;
//
//        // Coleta Personalização
//
        tipo = 2; // tipo de notificação: coleta pesonalizacao
//        calendar.clear();
//        calendar.set(2016, 0, 21, 17,15);
        //  setAlarme(calendar, tipo, id);


        id++;


        // Interrupções

        tipo = 3; // tipo de notificação: interrupções
        //calendar.clear();
        // calendar.set(2016, 0, 22, 01, 44,59);
        //calendar.set(Calendar.SECOND, 30);
        setAlarme(calendar, tipo, id);


    } //end onCreate

    private void setAlarme(Calendar calendar, int tipo, int id) {

        System.out.println(Calendar.getInstance().getTime());

        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm");
        String date = simpleFormat.format(calendar.getTimeInMillis());

        Intent myIntent = new Intent(MainActivity.this, MyBroadcastReceiver.class);
        myIntent.putExtra("tipo", tipo);
        myIntent.putExtra("hora", date);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, id, myIntent, 0);

        if (tipo == 3)
        {

            MyLocationListener gps = new MyLocationListener(MainActivity.this);
            if (gps.canGetLocation()) {
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();
                System.out.println("ansdja");
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
        System.out.println(date);

        // if (calendar.getTimeInMillis() - System.currentTimeMillis() >= 0) {

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 10, pendingIntent);
        finish();
        ;
//        }
//        else{
//
//
//            System.out.println("errorrrrrrrrrrrrrrrr");
//        }

    }
}