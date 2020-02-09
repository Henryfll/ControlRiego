package com.example.controlriego.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.controlriego.Fragment.DialogoGoteroFragment;
import com.example.controlriego.Models.LoteModel;
import com.example.controlriego.R;

import java.util.ArrayList;

public class LoteAdapter extends BaseAdapter {

    private Context context;
    ArrayList<LoteModel> listLotes = new ArrayList<>();
    private static LayoutInflater inflater = null;
    TextView txtNombrePropiedad;
    private ImageButton btnDialogoGoteroLote;

    @SuppressLint("MissingPermission")
    public LoteAdapter(Context context, ArrayList<LoteModel> listLotes) {
        this.context = context;
        this.listLotes = listLotes;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listLotes.size();
    }

    @Override
    public Object getItem(int i) {
        return listLotes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = inflater.inflate(R.layout.lote_item,null);



        txtNombrePropiedad = view.findViewById(R.id.nombre_item_lote);
        txtNombrePropiedad.setText(listLotes.get(position).getNombre());

        btnDialogoGoteroLote = view.findViewById(R.id.btn_goterosLote);
        btnDialogoGoteroLote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity activity = (FragmentActivity)(context);
                FragmentManager fm = activity.getSupportFragmentManager();
                DialogoGoteroFragment dialog = new DialogoGoteroFragment();
                dialog.show(fm, "MyCustomDialog");
            }
        });
        return view;
    }
}
