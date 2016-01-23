package com.example.root.annoyme;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created Gabriela.
 */
public class AgoraNao extends AppCompatActivity implements NoticeDialogFragment.NoticeDialogListener{

    private ArrayList<String> listaRespostas;
    private Double longitude, latitude;
    private Dados dados;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //System.out.println("aqui");

        dados = new Dados();


        if (dados.ArmazenamentoDisponivel(this)) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //System.out.println("Permissão dada");
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    //System.out.println("Deve explicar o racionale");
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                } else {
                    // No explanation needed, we can request the permission.
                    //System.out.println("Deve só pedir permissão");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    // 1 is an app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                //System.out.println("Permissão dada");
            }
        }

        listaRespostas = getIntent().getStringArrayListExtra("respostas");
        //System.out.println(listaRespostas);
        latitude = getIntent().getDoubleExtra("latitude", 0);
        longitude = getIntent().getDoubleExtra("longitude",0);
        //  setContentView(R.layout.main);
        showNoticeDialog();



    }

    public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        Bundle args = new Bundle();
        args.putStringArrayList("resposta", listaRespostas);
        args.putDouble("latitude", latitude);
        args.putDouble("longitude", longitude);

        DialogFragment dialog = new NoticeDialogFragment();
        dialog.setArguments(args);
        dialog.show(AgoraNao.this.getFragmentManager(), "NoticeDialogFragment");


        AgoraNao.this.getSupportFragmentManager().executePendingTransactions();
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    /*@Override
    public void onDialogPositiveClick(DialogFragment dialog, ArrayList listRespostas) {
        // User touched the dialog's positive button
        ArrayList<String> respostasUser = new ArrayList<String>();
        respostasUser.add(date);
        respostasUser.add(String.valueOf(latitude));
        respostasUser.add(String.valueOf(longitude));

        final String[] answers = getResources().getStringArray(R.array.userStudy_agoraNao_r);
        for (int i = 0; i < listRespostas.size(); i++) {
            //System.out.println(answers[((int) listRespostas.get(i))]);
            respostasUser.add(answers[((int) listRespostas.get(i))]);

        }

    ///    programarAgoraNao();

        Intent nextActivity = new Intent(AgoraNao.this, AgoraNao1.class);

        //   nextActivity.putStringArrayListExtra("respostas", listaRespostas);
        nextActivity.putStringArrayListExtra("respostas", respostasUser);
        startActivity(nextActivity);

    }*/

    private void programarAgoraNao()
    {

        // salvar localização GPS
        // usar alarm e programar as 20h ou deixar pendente
        // arrumar uma forma de deixar pendente

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ////System.out.println("Permission Granted!");
                    dados.criarDiretorio();
                } else {
                    ////System.out.println("Não será possível salvar suas respostas. Entre em contato, por favor.");
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}