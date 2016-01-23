package com.example.root.annoyme;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Gabriela.
 */
public class BackgroundService extends Service {

    private NotificationManager notificationManager;
    private int NOTIFICATION = R.string.userStudy_notificacao;
    private int tipo;
    private String date;
    private Double latitude, longitude;
    private ArrayList<String> listaRespostas;

    private final IBinder mBinder = new LocalBinder();


    public class LocalBinder extends Binder {
        BackgroundService getService() {
            return BackgroundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    @Override
    public void onCreate()
    {

    }


    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        notificationManager.cancel(NOTIFICATION);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {


        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        if(intent != null) {
            tipo = intent.getIntExtra("tipo", 0);
            listaRespostas = intent.getStringArrayListExtra("respostas");

            if(tipo == 3)
            {
                latitude = intent.getDoubleExtra("latitude", 0);
                longitude = intent.getDoubleExtra("longitude", 0);
            }
        }
        else {
            tipo = 99;
        }


        System.out.println(tipo);

        switch (tipo)
        {
            case 0:
                showNotificacaoInicial();
                break;
            case 1:
                showNotificationColetaDemo();
                break;
            case 2:
                showNotificationColetaPerso();
                break;
            case 3:
                showNotification(date, latitude, longitude);
                break;


        }


        return START_STICKY;
    }

    private void showNotificationColetaPerso() {

        Intent coletaperso = new Intent(this, ColetaPerso1.class);
        coletaperso.putExtra("respostas", listaRespostas);

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,coletaperso, 0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)  // the status icon
                .setContentTitle(getText(R.string.app_name))  // the label of the entry
                .setContentText(getText(R.string.notificacao_coletaPerso))  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Send the notification.
        notificationManager.notify(NOTIFICATION, notification);
    }

    private void showNotificationColetaDemo() {


        Intent coletademo = new Intent(this, ColetaDemo1.class);
        coletademo.putExtra("respostas", listaRespostas);

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, coletademo, 0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)  // the status icon
                .setContentTitle(getText(R.string.app_name))  // the label of the entry
                .setContentText(getText(R.string.notificacao_coletaDemo))  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Send the notification.
        notificationManager.notify(NOTIFICATION, notification);

    }

    private void showNotificacaoInicial()
    {
        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)  // the status icon
                .setTicker("ola")  // the status text
                .setContentTitle(getText(R.string.userStudy_notificacao))  // the label of the entry
                .setContentText(getText(R.string.app_name))  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Send the notification.
        notificationManager.notify(NOTIFICATION, notification);


    }


    private void showNotification(String date, Double latitude, Double longitude)
    {


        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = getText(R.string.userStudy_notificacao);

        // The PendingIntent to launch our activity if the user selects this notification

        System.out.println("2 " + listaRespostas.get(0));

        Intent agoraNao = new Intent(this, AgoraNao.class);
        agoraNao.putExtra("respostas", listaRespostas);
        agoraNao.putExtra("latitude", latitude);
        agoraNao.putExtra("longitude", longitude);
        PendingIntent piAgoraNao = PendingIntent.getActivity(this, 0, agoraNao, 0);

        Intent userStudy = new Intent(this, UserStudy.class);
        userStudy.putExtra("respostas", listaRespostas);

        PendingIntent piUserStudy = PendingIntent.getActivity(this, 0, userStudy, 0);


        // Set the info for the views that show in the notification panel.
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)  // the status icon
                .setTicker(text)  // the status text
                .setContentTitle(getText(R.string.userStudy_notificacao))  // the label of the entry
                .setContentText(text)  // the contents of the entry
                .setAutoCancel(true)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(piUserStudy)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getText(R.string.notificacao_coletaDemo)))
                .addAction(0,
                        getString(R.string.userStudy_notificacao_r1), piAgoraNao)
                .addAction(0,
                        getString(R.string.userStudy_notificacao_r2), piUserStudy)
                .build();


        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Send the notification.
        notificationManager.notify(NOTIFICATION, notification);

    }

}