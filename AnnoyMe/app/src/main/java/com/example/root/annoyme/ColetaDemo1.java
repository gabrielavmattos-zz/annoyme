package com.example.root.annoyme;

import android.content.Context;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.coletademo1);

        createRadioButtonP3();
        createRadioButtonP5();

        /********** Salvar as informações **********/
        btnAvancar = (Button) findViewById(R.id.button_coletademo1);

        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<String> listaRespostas = new ArrayList<String>();

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

                Intent nextActivity = new Intent(ColetaDemo1.this, ColetaDemo2.class);
                nextActivity.putStringArrayListExtra("respostas", listaRespostas);
                startActivity(nextActivity);

                finish();

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
}
