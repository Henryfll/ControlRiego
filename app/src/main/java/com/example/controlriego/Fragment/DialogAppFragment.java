package com.example.controlriego.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;



import com.example.controlriego.Adapters.CharacterAdapter;
import com.example.controlriego.R;


public class DialogAppFragment extends DialogFragment {

    /* La actividad que crea una instancia de este fragmento de diálogo debe
      implementar esta interfaz para recibir devoluciones de llamadas de eventos.
      Cada método pasa por el DialogFragment en caso de que el host necesite consultarlo.
   */
    public interface NoticeDialogListener {
        void onDialogConfirmClick(DialogFragment dialog);
        void onDialogCancelClick(DialogFragment dialog);
    }

    // Se usa esta instancia de la interfaz para entregar eventos de acción.
    NoticeDialogListener mListener;

    private static final int DIALOG_SIGN_OFF = 1; // Para saber que tipo de Dialogo es.

    private CharacterAdapter characterAdapter;

    public DialogAppFragment() {} // Constructor.

    public CharacterAdapter getCharacterAdapter() { return characterAdapter; }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        // Se crea el diálogo y se configura los manejadores de clic del botón.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        int typeDialog = 0; // Variable para controlar que tipo de Dialogo es.

        if (getArguments() != null) {
            typeDialog = getArguments().getInt("Type_Dialog");
        }

        // Se le añaden el icono, titulo y el mensaje que va a tener el dialogo segun sea el Dialogo.
        switch (typeDialog) {
            case DIALOG_SIGN_OFF:
                builder.setIcon(R.drawable.ic_exit_to_app);
                builder.setTitle("Cerrar Sesión");
                builder.setMessage("¿Desea cerrar la sesión?");
                break;

        }

        // Se añaden las acciones de los botones.
        builder.setPositiveButton(R.string.dialog_confirm_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Se envia el evento del botón salir a la actividad principal.
                mListener.onDialogConfirmClick(DialogAppFragment.this);
            }
        });

        builder.setNegativeButton(R.string.dialog_cancel_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Se enviaa el evento del botón cancelar a la actividad principal.
                mListener.onDialogCancelClick(DialogAppFragment.this);
            }
        });

        return builder.create();
    }

    // Este método invalida el Fragment.onAttach() para crear una instancia del NoticeDialogListener.
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verifica que la actividad del host implemente la interfaz de devolución de llamada.
        try {
            // Crea una instancia del NoticeDialogListener para que podamos enviar eventos al host.
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // La actividad no implementa la interfaz, en este caso se debe arrojar una excepción.
            throw new ClassCastException(activity.toString()
                    + " se debe implementar con NoticeDialogListener.");
        }
    }
}
