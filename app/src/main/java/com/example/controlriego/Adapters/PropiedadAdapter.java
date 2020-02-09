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
import com.example.controlriego.ControlActivity;
import com.example.controlriego.Models.FincaModel;
import com.example.controlriego.Models.PropiedadesModel;
import com.example.controlriego.R;

import java.util.ArrayList;

public class PropiedadAdapter extends BaseAdapter {

    Context context;
    ArrayList<FincaModel> listPropiedades = new ArrayList<>();
    private static LayoutInflater inflater = null;
    TextView txtTituloPropiedad, txtSubtipo;
    ImageView imageView;

    @SuppressLint("MissingPermission")
    public PropiedadAdapter(Context context, ArrayList<FincaModel> listPropiedades) {
        this.context = context;
        this.listPropiedades = listPropiedades;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listPropiedades.size();
    }

    @Override
    public Object getItem(int position) {
        return listPropiedades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null)
            view = inflater.inflate(R.layout.propiedad_item,null);
        txtSubtipo = (TextView) view.findViewById(R.id.subtipo_item_atractivo);

        //txtSubtipo.setText(listPropiedades.get(position).getCategoria());


        txtTituloPropiedad = view.findViewById(R.id.titulo_item_atractivo);
        txtTituloPropiedad.setText(listPropiedades.get(position).getNombre());
        imageView = (ImageView) view.findViewById(R.id.imgView_item_atractivo);
        //Glide.with(context).load(listPropiedades.get(position).getPathImagen()).centerCrop().into(imageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ControlActivity.class);

                intent.putExtra("propiedadNombre", listPropiedades.get(position).getNombre());

                context.startActivity(intent);
            }
        });
        return view;
    }
}
