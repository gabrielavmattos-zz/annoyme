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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GabrielaMattos.
 */
public class ColetaDemo2 extends AppCompatActivity
{

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnAvancar;
    private ArrayList<String> listaRespostas;
    private Context context;
    private Dados dados;
    private boolean status;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coletademo2);

        dados = new Dados();
        context = this;

        listaRespostas = getIntent().getStringArrayListExtra("respostas");

        /********** Salvar as informações **********/
        btnAvancar = (Button) findViewById(R.id.button_coletademo2);


        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                status = true;
                String text;

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

                if(status) {

                    Intent nextActivity = new Intent(ColetaDemo2.this, ColetaDemo3.class);
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
