package com.example.root.annoyme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public boolean salvarDados(ArrayList<String> listaRespostas, String label) {
        FileOutputStream data;
        try {
            char separador = ',';
            if(label.contains("coletaPerso")) {
                data = new FileOutputStream(diretorio.getAbsolutePath()+"/"+label+".csv");
                separador = '\n';
            }
            else
                data = new FileOutputStream(diretorio.getAbsolutePath()+"/"+label+"_"+listaRespostas.get(listaRespostas.size()-1)+".csv");

            for( int i = 0; i < listaRespostas.size(); i++) {
                //System.out.println(i+" - "+listaRespostas.get(i));
                data.write((listaRespostas.get(i) + separador).getBytes());
            }
            data.close();
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean salvarAgoraNao(ArrayList<String> listaRespostas) {
        BufferedWriter data;
        try {
            data = new BufferedWriter(new FileWriter(diretorio.getAbsolutePath()+"/agoraNao.csv", true));
            data.append("pendente,");
            for( int i = 0; i < listaRespostas.size(); i++)
            {
                //System.out.println(i+" - "+listaRespostas.get(i));
                data.append(listaRespostas.get(i) + ",");
            }
            data.append("\n");
            data.close();
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList verificarArquivoPendentes(int tipo, ArrayList<String> lista) {
        try {
            if(tipo == 0) {
                if(new File(diretorio.getAbsolutePath() + "/agoraNao.csv").exists()) {
                    InputStream in = new FileInputStream(diretorio.getAbsolutePath() + "/agoraNao.csv");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    ArrayList<String> line = new ArrayList<String>();
                    String text = reader.readLine();;
                    // read every line of the file into the line-variable, on line at the time
                    do {
                        System.out.println(text);
                        line.add(text);
                        text = reader.readLine();
                        // do something with the line
                    } while (text != null);
                    reader.close();
                    return line;
                }
                return null;
            }
            else {
                File file = new File(diretorio.getAbsolutePath() + "/agoraNao.csv");
                if(file.exists()) {
                    //System.out.println("aqui");
                    file.delete();
                }

                BufferedWriter data = new BufferedWriter(new FileWriter(diretorio.getAbsolutePath()+"/agoraNao.csv", true));

                for( int i = 0; i < lista.size(); i++) {
                    //System.out.println(i+" - "+listaRespostas.get(i));
                    data.append(lista.get(i) + "\n");
                }

                data.close();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList lerCenarios()
    {
        try {
            InputStream in = new FileInputStream(diretorio.getAbsolutePath() + "/coletaPerso.csv");
            if(in == null) {
                return null;
            }
            else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                ArrayList<String> line = new ArrayList<String>();
                String text = reader.readLine();;
                // read every line of the file into the line-variable, on line at the time
                do {
                    System.out.println(text);
                    line.add(text);
                    text = reader.readLine();

                    // do something with the line
                } while (text != null);

                reader.close();
                return line;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean verificarEtapa(String label)
    {

        try {
            InputStream in = new FileInputStream(diretorio.getAbsolutePath() + "/"+label+".csv");
            if(in == null) {
                return false;

            }
            else
                return true;
        } catch (IOException e) {
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