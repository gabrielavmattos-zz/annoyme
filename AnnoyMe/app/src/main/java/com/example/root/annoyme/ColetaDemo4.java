package com.example.root.annoyme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Created by Gabriela Mattos.
 */
public class ColetaDemo4 extends AppCompatActivity
{
    private RadioGroup radioGroup;
    private Button btnAvancar;
    private RadioButton radioButton;
    private ArrayList<String> listaRespostas;
    private Context context;
    private Dados dados;
    private boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coletademo4);

        listaRespostas = getIntent().getStringArrayListExtra("respostas");
        dados = new Dados();
        context = this;


        /********** Salvar as informações **********/
        btnAvancar = (Button) findViewById(R.id.button_coletademo4);


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


                if(status) {

                    Intent nextActivity = new Intent(ColetaDemo4.this, ColetaDemo5.class);
                    nextActivity.putStringArrayListExtra("respostas", listaRespostas);
                    startActivity(nextActivity);

                    finish();
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
