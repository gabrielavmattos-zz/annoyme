package com.example.root.annoyme;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity
{
    private int tipo;
    private PendingIntent pendingIntent;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int id = 0; //usado para setar vários alarmes, já que é necessário um pendingIntent  para cada alarme

        Calendar calendar = Calendar.getInstance();

        // Coleta Demografica

        tipo = 1; // tipo de notificação: coleta demografica
        calendar.clear();
        calendar.set(2016, 0, 21, 17, 11);
        setAlarme(calendar, tipo, id);
//
       id++;
//
//        // Coleta Personalização

        tipo = 2; // tipo de notificação: coleta pesonalizacao
        calendar.clear();
        calendar.set(2016, 0, 21, 17,15);
        setAlarme(calendar, tipo, id);


        id++;


        // Interrupções

        tipo = 3; // tipo de notificação: interrupções
        calendar.clear();
        calendar.set(2016, 0, 21, 17, 15,30);
                //calendar.set(Calendar.SECOND, 30);
        setAlarme(calendar, tipo, id);



    } //end onCreate

    private void setAlarme(Calendar calendar, int tipo, int id)
    {
        System.out.println(calendar.getTime() + " - " +tipo);
        System.out.println(Calendar.getInstance().getTime());

        if (calendar.getTimeInMillis() - System.currentTimeMillis() >= 0) {
            Intent myIntent = new Intent(MainActivity.this, MyBroadcastReceiver.class);
            myIntent.putExtra("tipo", tipo);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, id, myIntent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
        else{


            System.out.println("errorrrrrrrrrrrrrrrr");
        }

    }
}