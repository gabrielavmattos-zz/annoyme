package com.example.root.annoyme;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Gabriela.
 */
public class UserStudy extends AppCompatActivity implements NoticeDialogFragment_UserStudy.NoticeDialogListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        System.out.println("aqui-user");

        //  setContentView(R.layout.main);
        showNoticeDialog();
    }



    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new NoticeDialogFragment();
        dialog.show(this.getFragmentManager(), "NoticeDialogFragment");
    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button

        //SALVAR LOG

        ArrayList<String> listaRespostas = new ArrayList<String>();

//        Intent nextActivity = new Intent(UserStudy.this, UserStudy1.class);
//        listaRespostas.add("QueroEscolher");
//
//     //   nextActivity.putStringArrayListExtra("respostas", listaRespostas);
//        nextActivity.putStringArrayListExtra("respostas", listaRespostas);
//        startActivity(nextActivity);

      //  finish();



    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's positive button


        ArrayList<String> listaRespostas = new ArrayList<String>();

        Intent nextActivity = new Intent(UserStudy.this, UserStudy1.class);
        listaRespostas.add("QueroDelegar");

        //   nextActivity.putStringArrayListExtra("respostas", listaRespostas);
        nextActivity.putStringArrayListExtra("respostas", listaRespostas);
        startActivity(nextActivity);

    }



}
