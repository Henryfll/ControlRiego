package com.example.controlriego.Fragment;


import android.content.Context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.controlriego.Adapters.GoteroLoteAdapter;
import com.example.controlriego.Models.GoterosLotesModel;
import com.example.controlriego.R;
import com.example.controlriego.TransaccionesBDD;

import java.util.ArrayList;

public class DialogoGoteroFragment extends DialogFragment {
    private static final String TAG = "MyCustomDialog";
    private long idLote;
   /* public interface OnInputListener{
        void sendInput(String input);
    }
    public OnInputListener mOnInputListener;*/

    //widgets

    private ListView listView; //  listview donde se cargaran la lista de atractivos


    public ArrayList<GoterosLotesModel> listaGoterosLote = new ArrayList<>(); // Array de atractivos


    private TextView mActionOk;


    //vars

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_goteros, container, false);
        getDialog().setTitle("Goteros por Lote");

        listView = view.findViewById(R.id.listViewGoterosLote);

        mActionOk = view.findViewById(R.id.action_ok);

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input");

                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* try{
            mOnInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
        }*/
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getArguments() != null) {
            idLote = getArguments().getLong("idLote");
        }
        ConsultarGoterosLote();

    }

    private void ConsultarGoterosLote() {
        TransaccionesBDD transaccion = new TransaccionesBDD(getContext());
        listaGoterosLote = transaccion.consultarGoterosLotesbyIDLote(idLote);
        if(getContext()!= null) {
            listView.setAdapter(new GoteroLoteAdapter(getContext(),listaGoterosLote));
        }
    }
}