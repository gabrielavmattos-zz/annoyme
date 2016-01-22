package com.example.root.annoyme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by gabriela on 21/01/16.
 */
public class NoticeDialogFragment extends DialogFragment {

    private ArrayList mSelectedItems;
    private String date;
    private Double longitude, latitude;

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
      //  public void onDialogPositiveClick(DialogFragment dialog, ArrayList mSelectedItems);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

      //  getActivity()
        date = getArguments().getString("hora");
        System.out.println("hora certa: "+ date);
        latitude = getActivity().getIntent().getDoubleExtra("latitude",0);
        longitude = getActivity().getIntent().getDoubleExtra("longitude", 0);

        mSelectedItems = new ArrayList();  // Where we track the selected items

        System.out.println("aqui "+  getActivity().getIntent().getDoubleExtra("latitude",0));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getText(R.string.userStudy_agoraNao_p))
                .setMultiChoiceItems(R.array.userStudy_agoraNao_r, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            mSelectedItems.add(which);
                        } else if (mSelectedItems.contains(which)) {
                            // Else, if the item is already in the array, remove it
                            mSelectedItems.remove(Integer.valueOf(which));
                        }
                    }
                })
                .setPositiveButton(R.string.button_enviar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event b
                        ArrayList<String> respostasUser = new ArrayList<String>();
                        respostasUser.add(date);

                        System.out.println(longitude);

                        System.out.println(latitude);
                        respostasUser.add(String.valueOf(latitude));
                        respostasUser.add(String.valueOf(longitude));
                        System.out.println(date);

                        System.out.println(longitude);

                        System.out.println(latitude);

                        final String[] answers = getResources().getStringArray(R.array.userStudy_agoraNao_r);
                        for (int i = 0; i < mSelectedItems.size(); i++) {
                            System.out.println(answers[((int) mSelectedItems.get(i))]);
                            respostasUser.add(answers[((int) mSelectedItems.get(i))]);

                        }

                        ///    programarAgoraNao();

                        Intent nextActivity = new Intent(getActivity(), AgoraNao1.class);

                        //   nextActivity.putStringArrayListExtra("respostas", listaRespostas);
                        nextActivity.putStringArrayListExtra("respostas", respostasUser);
                        startActivity(nextActivity);


                    }
                });
        return builder.create();
    }


/*
    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
       super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the NoticeDialogListener so we can send events to the
			// host
			mListener = (NoticeDialogListener)activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ " must implement NoticeDialogListener");
		}
    }
*/

}
