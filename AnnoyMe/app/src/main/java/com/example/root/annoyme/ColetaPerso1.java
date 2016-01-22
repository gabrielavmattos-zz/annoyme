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
    private Dados dados;
    private TextView text, textTitle;
    private Button buttonProximo;
    private AlertDialog.Builder alert;
    private Context context;
    private String date;
    private ArrayList<String> listaRespostas;
    private  ArrayList<String> listaRespostas2;
    private  ArrayList<String> listaRespostas3;
    private Cenario cenario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coletaperso1);

        dados = new Dados();
        cenario = new Cenario();

        context = this;
        date = getIntent().getStringExtra("hora");
        listaRespostas = new ArrayList<String>();

        listaRespostas.add(date);

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


                    if (selectedId == -1) {

                        dados.exibeDialogo("É necessário escolher pelo menos uma alternativa.", context);
                    } else {

                        if (selectedId == 0) {
                            cenario.setNaoPreocupa(answers[i]);
                            listaRespostas.add("0/"+answers[i]);
                        } else if (selectedId == 1) {
                            cenario.setNormal(answers[i]);
                            listaRespostas.add("1/" + answers[i]);
                        }
                        else if (selectedId == 2) {
                            cenario.setMePreocupo(answers[i]);
                            listaRespostas.add("2/" + answers[i]);
                        }

                        if (i < answers.length - 1) {
                            text.setText(answers[i + 1]);
                        } else
                            sair();

                        if (i == answers.length - 2) {
                            buttonProximo.setText(R.string.button_enviar);
                        }
                        i++;
                        textTitle.setText("ETAPA " + (i + 1) + " DE " + answers.length);
                    }

                }
            });



    }

    private void sair() {

        if(dados.salvarDados(listaRespostas, "coletaPerso"))
        {

            alert= new AlertDialog.Builder(context);
            alert.setTitle("Concluído.");
            alert.setMessage("Questionário finalizado com sucesso!").setCancelable(false).setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
            alert.create().show();

        }
        else
        {

            alert= new AlertDialog.Builder(context);
            alert.setTitle("ERRO");
            alert.setPositiveButton("Ok", null);
            alert.setMessage("Erro em salvar o formulário.");
            alert.create().show();

        }
    }
}
