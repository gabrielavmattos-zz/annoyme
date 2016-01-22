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

    private   ArrayList<String> listaRespostas;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //System.out.println("aqui-user");

      listaRespostas = getIntent().getStringArrayListExtra("respostas");

        System.out.println("aqui-user: " + listaRespostas.get(0));

        //  setContentView(R.layout.main);
        showNoticeDialog();
    }



    public void showNoticeDialog() {

        Bundle args = new Bundle();
        args.putStringArrayList("resposta", listaRespostas);
        System.out.println("aqui1");
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new NoticeDialogFragment_UserStudy();


        dialog.setArguments(args);
        System.out.println(dialog.getArguments());
        dialog.show(this.getFragmentManager(), "NoticeDialogFragment");
        this.getSupportFragmentManager().executePendingTransactions();
    }




}
