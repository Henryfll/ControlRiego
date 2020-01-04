package com.example.controlriego.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.controlriego.R;


public class AcercaDeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Button btnSincronizacion;

    // Required empty public constructor
    public AcercaDeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acerca_de, container, false);
        btnSincronizacion= (Button) view.findViewById(R.id.btn_sincronizar);
        btnSincronizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SincronizacionAutorizacionFragment sf=new SincronizacionAutorizacionFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragment, sf).commit();
                /*getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment,sf)
                        .addToBackStack(null)
                        .commit();*/
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private Intent socialIntent(String url) {
        Uri uri = Uri.parse(url);
        return new Intent(Intent.ACTION_VIEW, uri);
    }


}
