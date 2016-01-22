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

    private String date;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        System.out.println("aqui-user");



        date = getIntent().getStringExtra("hora");

        //  setContentView(R.layout.main);
        showNoticeDialog(date);
    }



    public void showNoticeDialog(String date) {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new NoticeDialogFragment_UserStudy();
        dialog.show(this.getFragmentManager(), "NoticeDialogFragment");
    }




}
