package com.example.root.annoyme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created Gabriela.
 */
public class AgoraNao extends AppCompatActivity implements NoticeDialogFragment.NoticeDialogListener{

    private String date;
    private Double latitude, longitude;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        System.out.println("aqui");

        date = getIntent().getStringExtra("hora");
        latitude = getIntent().getDoubleExtra("latitude",0);
        longitude = getIntent().getDoubleExtra("longitude", 0);


      //  setContentView(R.layout.main);
                showNoticeDialog();
     }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new NoticeDialogFragment();
        dialog.show(this.getFragmentManager(), "NoticeDialogFragment");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, ArrayList listRespostas) {
        // User touched the dialog's positive button
        ArrayList<String> respostasUser = new ArrayList<String>();
        respostasUser.add(date);
        respostasUser.add(String.valueOf(latitude));
        respostasUser.add(String.valueOf(longitude));

        final String[] answers = getResources().getStringArray(R.array.userStudy_agoraNao_r);
        for (int i = 0; i < listRespostas.size(); i++) {
            System.out.println(answers[((int) listRespostas.get(i))]);
            respostasUser.add(answers[((int) listRespostas.get(i))]);

        }

        programarAgoraNao();

        Intent nextActivity = new Intent(AgoraNao.this, AgoraNao1.class);

        //   nextActivity.putStringArrayListExtra("respostas", listaRespostas);
        nextActivity.putStringArrayListExtra("respostas", respostasUser);
        startActivity(nextActivity);

    }

    private void programarAgoraNao()
    {

        // salvar localização GPS
        // usar alarm e programar as 20h ou deixar pendente
        // arrumar uma forma de deixar pendente

    }

}
