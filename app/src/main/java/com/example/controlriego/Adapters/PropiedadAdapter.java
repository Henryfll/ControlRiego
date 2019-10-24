package com.example.controlriego.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.controlriego.Models.PropiedadesModel;
import com.example.controlriego.R;

import java.util.ArrayList;

public class PropiedadAdapter extends BaseAdapter {

    Context context;
    ArrayList<PropiedadesModel> listPropiedades = new ArrayList<>();
    private static LayoutInflater inflater = null;
    TextView txtTituloPropiedad, txtSubtipo;
    ImageView imageView;

    @SuppressLint("MissingPermission")
    public PropiedadAdapter(Context context, ArrayList<PropiedadesModel> listPropiedades) {
        this.context = context;
        this.listPropiedades = listPropiedades;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null)
            view = inflater.inflate(R.layout.propiedad_item,null);
        txtSubtipo = (TextView) view.findViewById(R.id.subtipo_item_atractivo);

        txtSubtipo.setText(listPropiedades.get(position).getCategoria());


        txtTituloPropiedad = view.findViewById(R.id.titulo_item_atractivo);
        txtTituloPropiedad.setText(listPropiedades.get(position).getNombre());
        imageView = (ImageView) view.findViewById(R.id.imgView_item_atractivo);
        Glide.with(context).load(listPropiedades.get(position).getPathImagen()).centerCrop().into(imageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(context, AtractivoActivity.class);
                intent.putExtra("atractivoKey", listAtractivo.get(position).getKey());
                intent.putExtra("atractivoNombre", listAtractivo.get(position).getNombre());
                intent.putExtra("distancia", finalDistanciaStr);
                context.startActivity(intent);*/
            }
        });
        return view;
    }
}
