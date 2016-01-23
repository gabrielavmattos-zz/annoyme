package com.example.root.annoyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Gabriela.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private int tipo, id_cenario;
    private Double latitude, longitude;
    private Intent intent1;
    @Override
    public void onReceive(Context context, Intent intent)
    {
        ArrayList<String> listaRespostas = new ArrayList<String>();

        if (intent.getExtras() != null)
        {
            tipo = intent.getIntExtra("tipo", 0);

            listaRespostas = intent.getStringArrayListExtra("respostas");
            System.out.println(tipo);
            if(tipo == 3)
            {
             //   id_cenario = intent.getIntExtra("lat")
                latitude = intent.getDoubleExtra("latitude", 0);
                longitude = intent.getDoubleExtra("longitude", 0);
            }
        }
        else {
            tipo = 0; // tipo de interrupção informando participação no estudo

            listaRespostas.add("0");
        }
        intent1 = new Intent(context, BackgroundService.class);
        intent1.putExtra("tipo", tipo);
        intent1.putExtra("respostas", listaRespostas);


        if(tipo == 3)
        {
            intent1.putExtra("latitude", latitude);
            intent1.putExtra("longitude", longitude);
        }

        System.out.println("1 "+listaRespostas.get(0));
        System.out.println("1 "+longitude);
        System.out.println("1 "+latitude);

        context.startService(intent1);


    }

}