package com.example.root.annoyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Gabriela.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private int tipo;
    private String date;
    private Double latitude, longitude;
    private Intent intent1;
    @Override
    public void onReceive(Context context, Intent intent)
    {


        if (intent.getExtras() != null)
        {
            tipo = intent.getIntExtra("tipo", 0);
            date = intent.getStringExtra("hora");
            //System.out.println(tipo);
            if(tipo == 3)
            {
                latitude = intent.getDoubleExtra("latitude", 0);
                longitude = intent.getDoubleExtra("longitude", 0);
            }
        }
        else
            tipo = 0; // tipo de interrupção informando participação no estudo

        intent1 = new Intent(context, BackgroundService.class);
        intent1.putExtra("tipo", tipo);
        intent1.putExtra("hora", date);


        if(tipo == 3)
        {
            intent1.putExtra("latitude", latitude);
            intent1.putExtra("longitude", longitude);
        }

        //System.out.println("1 "+date);
        //System.out.println("1 "+longitude);
        System.out.println("1 "+latitude);

        context.startService(intent1);


    }

}
