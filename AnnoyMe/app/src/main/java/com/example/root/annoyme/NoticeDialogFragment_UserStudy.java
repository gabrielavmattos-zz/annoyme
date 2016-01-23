package com.example.root.annoyme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gabriela on 21/01/16.
 */
public class NoticeDialogFragment_UserStudy extends DialogFragment {

    private ArrayList<String> listaRespostas;
    private Cenario cenario;
    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        cenario = new Cenario();

        System.out.println("aqui2");
        System.out.println(getActivity().getIntent().getStringArrayListExtra("respostas"));
        //      listaRespostas = getArguments().getStringArrayList("respostas");

        listaRespostas = getActivity().getIntent().getStringArrayListExtra("respostas");
        String cenario1 = cenario.getCenario(0,0);
        listaRespostas.add(cenario1);
//        System.out.println("hora certa: " + listaRespostas.get(0));
//        TextView textView = (TextView) getActivity().findViewById(R.id.interrupcao_cenario);
//        textView.setText(cenario1);

    //    listaRespostas.add(cenarios)

        System.out.println("aqui"); // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(inflater.inflate(R.layout.interrupcao, null))
             //   .setMessage(cenario1)
                .setPositiveButton(R.string.userStudy_interrupcao_r1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity]

                        Intent nextActivity = new Intent(getActivity(), UserStudy1.class);

                        listaRespostas.add("QueroEscolher");
                        //   nextActivity.putStringArrayListExtra("respostas", listaRespostas);
                        nextActivity.putStringArrayListExtra("respostas", listaRespostas);
                        startActivity(nextActivity);

                        // mListener1.onDialogPositiveClick(NoticeDialogFragment_UserStudy.this);
                    }
                })
                .setNegativeButton(R.string.userStudy_interrupcao_r2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity ArrayList<String> listaRespostas = new ArrayList<String>();

                        ArrayList<String> listaRespostas = new ArrayList<String>();
                        Intent nextActivity = new Intent(getActivity(), UserStudy1.class);

                        listaRespostas.add("QueroDelegar");
                        System.out.println("aqui delegar");
                        //   nextActivity.putStringArrayListExtra("respostas", listaRespostas);
                        nextActivity.putStringArrayListExtra("respostas", listaRespostas);
                        startActivity(nextActivity);

                    }
                });
        return builder.create();
    }



    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
  /*  @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the
            // host
            mListener1 = (NoticeDialogListener)activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }*/


}