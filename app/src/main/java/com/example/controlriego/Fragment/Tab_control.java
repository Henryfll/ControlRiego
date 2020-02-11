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
import android.widget.ListView;
import android.widget.TextView;

import com.example.controlriego.Adapters.LoteAdapter;
import com.example.controlriego.Models.LoteModel;
import com.example.controlriego.R;
import com.example.controlriego.TransaccionesBDD;

import java.util.ArrayList;


public class Tab_control extends Fragment {
    public TextView txtTitulo;
    public String keyatractivo ;
    public long idPropiedad;
    private ListView listView; //  listview donde se cargaran la lista de atractivos
    public ArrayList<LoteModel> listaLotes = new ArrayList<>(); // Array de atractivos

    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_control, container, false);
        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        //txtTitulo = (TextView) view.findViewById(R.id.txtTituloAtractivo);
        //getPropiedad();
        listView = (ListView) view.findViewById(R.id.listViewLotes);
        return view;
    }
    public void  getPropiedad(){
        //obtener la data
        txtTitulo.setText("Nombre de la propiedad");
    }
    @Override
    public void onStart() {
        super.onStart();
        ConsultarLotes();

    }
    public void ConsultarLotes(){
       /* LoteModel obj=new LoteModel();
        obj.setLot_codigo(0);
        obj.setNombre("Lote1");
        obj.setDescripcion("Finca para el control de riego de agua");

        listaLotes.add(obj);
        LoteModel obj1=new LoteModel();
        obj1.setLot_codigo(1);
        obj1.setNombre("Lote2");
        obj1.setDescripcion("Finca para el control de riego de agua");
        listaLotes.add(obj1);*/

        TransaccionesBDD transaccion = new TransaccionesBDD(getContext());

        listaLotes = transaccion.consultarLotesbyID(idPropiedad);

        if(getContext()!= null) {
            listView.setAdapter(new LoteAdapter(getContext(),listaLotes));
        }
    }
}
