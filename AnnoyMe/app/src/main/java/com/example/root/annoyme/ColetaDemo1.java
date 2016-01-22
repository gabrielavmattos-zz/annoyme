package com.example.root.annoyme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.Toast;


import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Gabriela Mattos.
 */

public class ColetaDemo1 extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private CheckBox checkBox;
    private Button btnAvancar;
    private boolean status, status2;
    private Context context;
    private Dados dados;
    private String date;
    private ArrayList<String> listaRespostas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.coletademo1);
        listaRespostas = new ArrayList<String>();

        dados = new Dados();
        context = this;
        date = getIntent().getStringExtra("hora");
        listaRespostas.add(date);
        createRadioButtonP3();
        createRadioButtonP5();

        /********** Salvar as informações **********/
        btnAvancar = (Button) findViewById(R.id.button_coletademo1);

        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = true;
                String text;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q1);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {
                    radioButton = (RadioButton) findViewById(selectedId);
                    text = (String) radioButton.getText();
                    listaRespostas.add(text);
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q2);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {
                    radioButton = (RadioButton) findViewById(selectedId);
                    text = (String) radioButton.getText();
                    listaRespostas.add(text);
                }
                else
                    status = false;

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q3);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {
                    radioButton = (RadioButton) findViewById(selectedId);
                    text = (String) radioButton.getText();
                    listaRespostas.add(text);
                }
                else
                    status = false;

                text = "";
                status2 = false;

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r1);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status2 = true;
                }

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r2);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status2 = true;
                }

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r3);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status2 = true;
                }
                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r4);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status2 = true;
                }

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r5);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status2 = true;
                }
                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r6);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status2 = true;
                }

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r7);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status2 = true;
                }

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r8);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status2 = true;
                }

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r9);
                if(checkBox.isChecked()) {

                    if(((EditText) findViewById(R.id.textOutros)).getText().length()>0) {
                        text += ((EditText) findViewById(R.id.textOutros)).getText().toString();
                        status2 = true;
                    }

                }
                if (status2)
                    listaRespostas.add(text);

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q5);
                selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId != -1) {
                    radioButton = (RadioButton) findViewById(selectedId);
                    text = (String) radioButton.getText();
                    listaRespostas.add(text);
                }
                else
                    status = false;

                if(status && status2) {
                    Intent nextActivity = new Intent(ColetaDemo1.this, ColetaDemo2.class);
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


    private void createRadioButtonP3() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q3);
        String[] answers = getResources().getStringArray(R.array.coletaDemo1_p3_r);

        for (int i = 0; i < answers.length; i++) {

            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answers[i]);
            radioGroup.addView(radioButton);

        }

    }


    private void createRadioButtonP5() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q5);
        String[] answers = getResources().getStringArray(R.array.coletaDemo1_p5_r);

        for (int i = 0; i < answers.length; i++) {

            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answers[i]);
            radioGroup.addView(radioButton);

        }

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
