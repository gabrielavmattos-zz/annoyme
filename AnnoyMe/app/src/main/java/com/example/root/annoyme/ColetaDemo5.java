package com.example.root.annoyme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by Gabriela Mattos.
 */
public class ColetaDemo5 extends AppCompatActivity
{

    private static final String TAG = "ManageFile";
    private RadioGroup radioGroup;
    private Button btnAvancar;
    private ArrayList<String> listaRespostas;
    private Context context;
    private AlertDialog.Builder alert;
    private RadioButton radioButton;
    private Dados dados;
    private boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coletademo5);

        context = this;
        dados = new Dados(context);

        listaRespostas = getIntent().getStringArrayListExtra("respostas");

        /********** Salvar as informações **********/
        btnAvancar = (Button) findViewById(R.id.button_coletademo5);


        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                status = true;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q1);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q2);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q3);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q4);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q5);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q6);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q7);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q8);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q9);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q10);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q11);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q12);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q13);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q14);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q15);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q16);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q17);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q18);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q19);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q20);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {

                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    listaRespostas.add(Integer.toString(selectedId));
                }
                else
                    status = false;
                if(status) {
                    if (dados.salvarDados(listaRespostas, "coletaDemo")) {

                        alert = new AlertDialog.Builder(context);
                        alert.setTitle("Concluído.");
                        alert.setMessage("Questionário finalizado com sucesso!").setCancelable(false).setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                    }
                                });
                        alert.create().show();

                    } else {

                        alert = new AlertDialog.Builder(context);
                        alert.setTitle("ERRO");
                        alert.setPositiveButton("Ok", null);
                        alert.setMessage("Erro em salvar o formulário.");
                        alert.create().show();

                    }

                    }
                    else
                    {
                        dados.exibeDialogo("Todas as questões devem ser respondida", context);
                    }

            }
        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Atenção");

        alertDialogBuilder.setMessage("Não é possível voltar para a janela anterior.").setCancelable(false).setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
