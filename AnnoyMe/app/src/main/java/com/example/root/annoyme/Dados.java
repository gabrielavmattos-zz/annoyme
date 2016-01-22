package com.example.root.annoyme;

import android.app.AlertDialog;
import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by gabriela on 22/01/16.
 */
public class Dados {

    public boolean salvarDados(ArrayList<String> listaRespostas, String label)
    {

        FileOutputStream data;


        try {
            File diretorio = new File("/mnt/sdcard/annoyme");

            if(!diretorio.exists())
            {
                System.out.println("Não tem ");
                diretorio.mkdir();
            }


            data = new FileOutputStream(diretorio.getAbsolutePath()+"/"+label+"_"+listaRespostas.get(0)+".csv");

            for( int i = 0; i < listaRespostas.size(); i++)
            {
                System.out.println(i+" - "+listaRespostas.get(i));
                data.write((listaRespostas.get(i) + ",").getBytes());
            }
            data.close();
            return true;


        } catch (java.io.IOException e) {

            e.printStackTrace();
            return false;
        }



    }



    public void exibeDialogo(String mensagem, Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setPositiveButton("OK", null);
        alert.setMessage(mensagem);
        alert.create().show();
    }
}
