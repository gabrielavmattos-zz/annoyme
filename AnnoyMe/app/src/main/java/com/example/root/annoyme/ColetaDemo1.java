package com.example.root.annoyme;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;


/**
 * Created by Gabriela Mattos.
 */

public class ColetaDemo1 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coletademo1);

        createRadioButtonP3();
        createRadioButtonP5();


    }

    private void createRadioButtonP3()
    {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioButton_p3);
        String[] answers = getResources().getStringArray(R.array.coletaDemo1_p3_r);

        for (int i = 0; i < answers.length; i++)
        {

            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answers[i]);
            radioGroup.addView(radioButton);

        }

    }


    private void createRadioButtonP5()
    {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioButton_p5);
        String[] answers = getResources().getStringArray(R.array.coletaDemo1_p5_r);

        for (int i = 0; i < answers.length; i++)
        {

            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answers[i]);
            radioGroup.addView(radioButton);

        }

    }
}
