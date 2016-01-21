package com.example.root.annoyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Gabriela.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private int tipo;
    private Intent intent1;
    @Override
    public void onReceive(Context context, Intent intent)
    {


        if (intent.getExtras() != null)
        {
            tipo = intent.getIntExtra("tipo", 0);
            System.out.println(tipo);
        }
        else
            tipo = 0; // tipo de interrupção informando participação no estudo

        intent1 = new Intent(context, BackgroundService.class);
        intent1.putExtra("tipo", tipo);
        //startServiceIntent.putStringArrayListExtra("respostas", listaRespostas);
        context.startService(intent1);


    }

}
