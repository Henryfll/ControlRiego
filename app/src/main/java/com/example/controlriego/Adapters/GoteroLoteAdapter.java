package com.example.controlriego.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.controlriego.Models.GoterosLotesModel;
import com.example.controlriego.R;

import java.util.ArrayList;

public class GoteroLoteAdapter extends BaseAdapter {

    private Context context;
    ArrayList<GoterosLotesModel> listGoterosLote = new ArrayList<>();
    private static LayoutInflater inflater = null;
    TextView txtNombreGotero;
    EditText txtGoterosLOte;


    @SuppressLint("MissingPermission")
    public GoteroLoteAdapter(Context context, ArrayList<GoterosLotesModel> listGoterosLote) {
        this.context = context;
        this.listGoterosLote = listGoterosLote;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listGoterosLote.size();
    }

    @Override
    public Object getItem(int i) {
        return listGoterosLote.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = inflater.inflate(R.layout.goterolote_item,null);



        txtNombreGotero = view.findViewById(R.id.nombre_item_gotero);
        txtNombreGotero.setText(listGoterosLote.get(position).getId_gotero()+"-");

       txtGoterosLOte = view.findViewById(R.id.gotero_num);
       txtGoterosLOte.setText(listGoterosLote.get(position).getCantidad());
        return view;
    }
}

