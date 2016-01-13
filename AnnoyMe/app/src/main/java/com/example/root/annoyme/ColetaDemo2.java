package com.example.root.annoyme;

import android.app.AlertDialog;
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
    private CheckBox checkBox;
    private Button btnAvancar;
    private ArrayList<String> listaRespostas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coletademo2);

        if(!getIntent().getStringArrayListExtra("respostas").isEmpty())
        {
            listaRespostas = getIntent().getStringArrayListExtra("respostas");
        }


        /********** Salvar as informações **********/
        btnAvancar = (Button) findViewById(R.id.button_coletademo2);


        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q1);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String text = (String) radioButton.getText();
                listaRespostas.add(text);

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q2);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                text = (String) radioButton.getText();
                listaRespostas.add(text);

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q3);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                text = (String) radioButton.getText();
                listaRespostas.add(text);

                text = "";

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r1);

                if(checkBox.isChecked())
                    text += (String) checkBox.getText() + '/';

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r2);

                if(checkBox.isChecked())
                    text += (String) checkBox.getText() + '/';

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r3);

                if(checkBox.isChecked())
                    text += (String) checkBox.getText() + '/';

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r4);

                if(checkBox.isChecked())
                    text += (String) checkBox.getText() + '/';

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r5);

                if(checkBox.isChecked())
                    text += (String) checkBox.getText() + '/';

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r6);

                if(checkBox.isChecked())
                    text += (String) checkBox.getText() + '/';

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r7);

                if(checkBox.isChecked())
                    text += (String) checkBox.getText() + '/';

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r8);

                if(checkBox.isChecked())
                    text += (String) checkBox.getText() + '/';

                checkBox = (CheckBox) findViewById(R.id.checkbox_p4_r9);

                if(checkBox.isChecked())
                    text +=  ((EditText) findViewById(R.id.textOutros)).getText().toString();

                listaRespostas.add(text);

                radioGroup = (RadioGroup) findViewById(R.id.radioGroup_q5);
                selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                text = (String) radioButton.getText();
                listaRespostas.add(text);

                Intent nextActivity = new Intent(ColetaDemo2.this, ColetaDemo3.class);
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
