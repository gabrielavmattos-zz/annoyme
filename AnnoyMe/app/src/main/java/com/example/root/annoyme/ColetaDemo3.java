package com.example.root.annoyme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Created by GabrielaMattos.
 */
public class ColetaDemo3 extends AppCompatActivity
{

    private RadioGroup radioGroup;
    private CheckBox checkBox;
    private Button btnAvancar;
    private RadioButton radioButton;
    private ArrayList<String> listaRespostas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coletademo3);

        createCheckListP1();

        listaRespostas = getIntent().getStringArrayListExtra("respostas");

        /********** Salvar as informações **********/
        btnAvancar = (Button) findViewById(R.id.button_coletademo3);


        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] answers = getResources().getStringArray(R.array.coletaDemo3_p1_r);
                String text = "";
                for (int i = 0; i < answers.length; i++)
                {

                    checkBox = (CheckBox) findViewById(i);
                    if (checkBox.isChecked())
                    {
                        System.out.println(checkBox.getText());
                        text += (String) checkBox.getText() + '/';


                    }
                }

                listaRespostas.add(text);

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q2);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                selectedId = radioGroup.indexOfChild(radioButton);
                listaRespostas.add(Integer.toString(selectedId));

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q3);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                selectedId = radioGroup.indexOfChild(radioButton);
                listaRespostas.add(Integer.toString(selectedId));

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q4);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                selectedId = radioGroup.indexOfChild(radioButton);
                listaRespostas.add(Integer.toString(selectedId));

                Intent nextActivity = new Intent(ColetaDemo3.this, ColetaDemo4.class);
                nextActivity.putStringArrayListExtra("respostas", listaRespostas);
                startActivity(nextActivity);

                finish();

            }
        });

    }

    private void createCheckListP1()
    {

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_p1);
        String[] answers = getResources().getStringArray(R.array.coletaDemo3_p1_r);

        for (int i = 0; i < answers.length; i++)
        {

            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(answers[i]);
            checkBox.setId(i);
            linearLayout.addView(checkBox);

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
