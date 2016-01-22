package com.example.root.annoyme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Context;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Created by gabriela on 21/01/16.
 */
public class UserStudy1 extends AppCompatActivity {

    private ArrayList<String> listaRespostas;
    private Button btnAvancar;
    private RadioGroup radioGroup;
    private Dados dados;
    private Context context;
    private RadioButton radioButton;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.userstudy1);

        context = this;
        dados = new Dados(context);

        listaRespostas = getIntent().getStringArrayListExtra("respostas");

        /********** Salvar as informações **********/
        btnAvancar = (Button) findViewById(R.id.button_userStudy1);


        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q1);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                selectedId = radioGroup.indexOfChild(radioButton);
                listaRespostas.add(Integer.toString(selectedId));

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q2);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String text = (String) radioButton.getText();
                listaRespostas.add(text);

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q3);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                text = (String) radioButton.getText();
                listaRespostas.add(text);

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q4);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                text = (String) radioButton.getText();
                listaRespostas.add(text);

                checkBox = (CheckBox) findViewById(R.id.checkbox_q4);
                text = "";
                if(checkBox.isChecked())
                    text = (String) checkBox.getText();


                listaRespostas.add(text);

                Intent nextActivity = new Intent(UserStudy1.this, UserStudy2.class);
                nextActivity.putStringArrayListExtra("respostas", listaRespostas);
                startActivity(nextActivity);

                finish();

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
