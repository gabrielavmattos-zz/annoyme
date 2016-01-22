package com.example.root.annoyme;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by gabriela on 22/01/16.
 */
public class Dados {
    private AlertDialog.Builder alert;
    private File diretorio;

    public Dados(Context context) {
        String estado = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(estado)) {
            // Leitura e Escrita Disponível
            System.out.println("R/W: " + Environment.getExternalStorageDirectory());

            diretorio = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/annoyme");
            boolean criou = false;

            if(!diretorio.exists())
            {
                System.out.println("Nao tem diretorio " + diretorio.getAbsolutePath());
                criou = diretorio.mkdir();
                if (!criou) System.out.println("Não criou.");
                else {
                    System.out.println("Criado.");
                }
            }
        } else {
            alert = new AlertDialog.Builder(context);
            alert.setTitle("ERRO");
            alert.setPositiveButton("Ok", null);
            alert.setMessage("Não será possível salvar suas respostas. Entre em contato, por favor.");
            alert.create().show();
        }
    }

    public boolean salvarDados(ArrayList<String> listaRespostas, String label)
    {
        FileOutputStream data;
        try {
            data = new FileOutputStream(diretorio.getAbsolutePath()+"/"+label+"_"+listaRespostas.get(0)+".csv");

            for( int i = 0; i < listaRespostas.size(); i++)
            {
                //System.out.println(i+" - "+listaRespostas.get(i));
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