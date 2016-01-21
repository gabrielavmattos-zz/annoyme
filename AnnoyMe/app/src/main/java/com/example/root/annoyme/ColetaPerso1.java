package com.example.root.annoyme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gabriela.
 */
public class ColetaPerso1 extends AppCompatActivity
{

    private RadioButton radioButton;
    private int i=0;
    private TextView text, textTitle;
    private Button buttonProximo;
    private AlertDialog.Builder alert;
    private Context context;
    private ArrayList<String> listaRespostas1;
    private  ArrayList<String> listaRespostas2;
    private  ArrayList<String> listaRespostas3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coletaperso1);

        context = this;
        listaRespostas1 = new ArrayList<String>();
        listaRespostas2 = new ArrayList<String>();
        listaRespostas3 = new ArrayList<String>();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_perso1);
        final String[] answers = getResources().getStringArray(R.array.coletaPerso1_p1_r);

        text = (TextView) findViewById(R.id.coletaPerso1_q);
        textTitle = (TextView) findViewById(R.id.coletaPerso_title);




        text.setText(answers[0]);
        textTitle.setText("ETAPA 1 DE "+answers.length);
        buttonProximo = (Button) findViewById(R.id.button_coletaperso1);


         /*   if (i == answers.length - 1) {

                sair();

            }*/
            buttonProximo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q1);
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(selectedId);
                    selectedId = radioGroup.indexOfChild(radioButton);
                    radioGroup.clearCheck();

                    if (selectedId == 0) {
                        System.out.println(i);

                        System.out.println(answers[i]);
                        listaRespostas1.add(answers[i]);
                    }
                    else if(selectedId == 1)
                        listaRespostas2.add(answers[i]);
                    else
                        listaRespostas3.add(answers[i]);


                    if (i < answers.length-1) {
                        text.setText(answers[i + 1]);
                    }
                    else
                        sair();

                    if( i == answers.length - 2)
                    {
                        buttonProximo.setText(R.string.button_enviar);
                    }
                    i++;

                    textTitle.setText("ETAPA "+(i+1)+" DE "+answers.length);

                }
            });



    }

    private void sair() {


        alert = new AlertDialog.Builder(context);
        alert.setTitle("Concluído.");
        alert.setMessage("Questionário finalizado com sucesso!").setCancelable(false).setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        alert.create().show();
    }
}
