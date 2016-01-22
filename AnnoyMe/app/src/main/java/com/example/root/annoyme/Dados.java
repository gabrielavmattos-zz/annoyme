package com.example.root.annoyme;

import android.app.Activity;
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

    private File diretorio = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/annoyme");

    public boolean ArmazenamentoDisponivel(Activity activity) {

        String estado = Environment.getExternalStorageState();
        AlertDialog.Builder alert;


        if (Environment.MEDIA_MOUNTED.equals(estado)) {
            // Leitura e Escrita Disponível
            System.out.println("R/W: " + Environment.getExternalStorageDirectory());
            return true;
        } else {
            alert = new AlertDialog.Builder(activity);
            alert.setTitle("ERRO");
            alert.setPositiveButton("Ok", null);
            alert.setMessage("Não será possível salvar suas respostas. Entre em contato, por favor.");
            alert.create().show();
            return false;
        }
    }

    public boolean criarDiretorio() {
        if(!diretorio.exists()){
            if (!(diretorio.mkdir() || diretorio.isDirectory())) {
                System.out.println("Não criou.");
                return false;
            }
            else {
                System.out.println("Criado.");
                return true;
            }
        }
        else {
            System.out.println("Diretorio ja existia.");
            return true;
        }
    }

    public boolean salvarDados(ArrayList<String> listaRespostas, String label)
    {
        FileOutputStream data;
        try {
						data = new FileOutputStream(diretorio.getAbsolutePath()+"/"+label+"_"+listaRespostas.get(listaRespostas.size()-1)+".csv");

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