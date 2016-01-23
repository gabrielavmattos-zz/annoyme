package com.example.root.annoyme;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gabriela.
 */
public class UserStudy2 extends AppCompatActivity {

    private ArrayList<String> listaRespostas;
    private Dados dados;
    private Button btnEnviar;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Context context;
    private AlertDialog.Builder alert;
    private CheckBox checkBox;
    private boolean status_p1, status_p2; // verifica se todos os campos foram respondidos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dados = new Dados();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.userstudy2);

        context = this;

        if (dados.ArmazenamentoDisponivel(this)) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                System.out.println("Permissão dada");
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    System.out.println("Deve explicar o racionale");
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed, we can request the permission.
                    System.out.println("Deve só pedir permissão");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    // 1 is an app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }
            else {
                System.out.println("Permissão dada");
            }
        }

        listaRespostas = getIntent().getStringArrayListExtra("respostas");


        /********** Salvar as informações **********/
        btnEnviar = (Button) findViewById(R.id.button_userStudy2);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                status_p1 = true ;
                status_p2 = false;

                String text = "";
//                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q5);
//                int selectedId = radioGroup.getCheckedRadioButtonId();
//                System.out.println(selectedId);
                if((radioButton = (RadioButton) findViewById(R.id.userStudy_p5_r1)).isChecked())
                {
                    text = (String) radioButton.getText();
                } else
                if((radioButton = (RadioButton) findViewById(R.id.userStudy_p5_r2)).isChecked())
                {
                    text = (String) radioButton.getText();
                } else
                if((radioButton = (RadioButton) findViewById(R.id.userStudy_p5_r3)).isChecked())
                {
                    text = (String) radioButton.getText();
                } else
                if((radioButton = (RadioButton) findViewById(R.id.userStudy_p5_r4)).isChecked())
                {
                    text = (String) radioButton.getText();
                } else
                if((radioButton = (RadioButton) findViewById(R.id.userStudy_p5_r5)).isChecked())
                {
                    text = (String) radioButton.getText();
                } else
                if((radioButton = (RadioButton) findViewById(R.id.userStudy_p5_r6)).isChecked())
                {
                    text = (String) radioButton.getText();
                } else
                if((radioButton = (RadioButton) findViewById(R.id.userStudy_p5_r7)).isChecked())
                {
                    text = (String) radioButton.getText();
                } else
                if((radioButton = (RadioButton) findViewById(R.id.userStudy_p5_r8)).isChecked())
                {
                    text = (String) radioButton.getText();
                } else
                if((radioButton = (RadioButton) findViewById(R.id.userStudy_p5_r9)).isChecked())
                {
                    text = (String) radioButton.getText();
                } else
                if((radioButton = (RadioButton) findViewById(R.id.userStudy_p5_r10)).isChecked())
                {
                    text = (String) radioButton.getText();
                } else if((radioButton = (RadioButton) findViewById(R.id.userStudy_p5_r11)).isChecked())
                {

                    if(((EditText) findViewById(R.id.userStudy_p5_textOutros)).getText().length()>0) {

                        text = ((EditText) findViewById(R.id.userStudy_p5_textOutros)).getText().toString();

                    }
                    else
                        status_p1 = false;

                }
                else {
                    status_p1 = false;
                }


                listaRespostas.add(text);

                text = "";

                checkBox = (CheckBox) findViewById(R.id.checkbox_p6_r1);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status_p2 = true;
                }
                checkBox = (CheckBox) findViewById(R.id.checkbox_p6_r2);


                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status_p2 = true;
                }
                checkBox = (CheckBox) findViewById(R.id.checkbox_p6_r3);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status_p2 = true;
                }

                checkBox = (CheckBox) findViewById(R.id.checkbox_p6_r4);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status_p2 = true;
                }

                checkBox = (CheckBox) findViewById(R.id.checkbox_p6_r5);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status_p2 = true;
                }
                checkBox = (CheckBox) findViewById(R.id.checkbox_p6_r6);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status_p2 = true;
                }

                checkBox = (CheckBox) findViewById(R.id.checkbox_p6_r7);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status_p2 = true;
                }
                checkBox = (CheckBox) findViewById(R.id.checkbox_p6_r8);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status_p2 = true;
                }

                checkBox = (CheckBox) findViewById(R.id.checkbox_p6_r9);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status_p2 = true;
                }

                checkBox = (CheckBox) findViewById(R.id.checkbox_p6_r10);

                if(checkBox.isChecked()) {
                    text += (String) checkBox.getText() + '/';
                    status_p2 = true;
                }

                checkBox = (CheckBox) findViewById(R.id.checkbox_p6_r11);

                if(checkBox.isChecked()) {

                    if(((EditText) findViewById(R.id.userStudy_p6_textOutros)).getText().length()>0) {
                        text += ((EditText) findViewById(R.id.userStudy_p6_textOutros)).getText().toString();
                        status_p2 = true;
                    }

                }

                if(status_p1 && status_p2) {
                    listaRespostas.add(text);

                    SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm");

                    listaRespostas.add(simpleFormat.format(new Date(System.currentTimeMillis())));

                    if (dados.salvarDados(listaRespostas, "int")) {

                        alert = new AlertDialog.Builder(context);
                        alert.setTitle("Concluído.");
                        alert.setMessage("Questionário finalizado com sucesso!").setCancelable(false).setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {{
                                        Intent nextActivity = new Intent(UserStudy2.this, MainActivity.class);
                                        startActivity(nextActivity);
                                        finish();
                                    }
                                }});
                        alert.create().show();

                    } else {

                        alert = new AlertDialog.Builder(context);
                        alert.setTitle("ERRO");
                        alert.setPositiveButton("Ok", null);
                        alert.setMessage("Erro em salvar o formulário.");
                        alert.create().show();

                    }
                }
                else{
                    dados.exibeDialogo("Todas as questões devem ser respondidas.", context);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    System.out.println("Permission Granted!");
                    dados.criarDiretorio();
                } else {
                    System.out.println("Não será possível salvar suas respostas. Entre em contato, por favor.");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}