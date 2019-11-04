package com.example.controlriego.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.controlriego.R;



public class Tab_control extends Fragment {
    public TextView txtTitulo;
    public String keyatractivo ;

    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_control, container, false);
        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        txtTitulo = (TextView) view.findViewById(R.id.txtTituloAtractivo);
        getPropiedad();
        return view;
    }
    public void  getPropiedad(){
        //obtener la data
        txtTitulo.setText("Nombre de la propiedad");
    }
}
