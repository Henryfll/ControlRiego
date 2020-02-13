package com.example.controlriego.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controlriego.ControlActivity;
import com.example.controlriego.Fragment.DialogoGoteroFragment;
import com.example.controlriego.Models.GoteroModel;
import com.example.controlriego.Models.GoterosLotesModel;
import com.example.controlriego.R;
import com.example.controlriego.TransaccionesBDD;

import java.util.ArrayList;

public class GoteroLoteAdapter extends BaseAdapter {

    private Context context;
    ArrayList<GoterosLotesModel> listGoterosLote = new ArrayList<>();
    private static LayoutInflater inflater = null;
    TextView txtNombreGotero;
    TextView txtGoterosLote;


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

        TransaccionesBDD transaccion = new TransaccionesBDD(context);
        GoteroModel gotero=transaccion.consultarGoterobyID(listGoterosLote.get(position).getId_gotero());

        txtNombreGotero = view.findViewById(R.id.nombre_item_gotero);
        txtNombreGotero.setText(gotero.getDescripcion()+":");

       txtGoterosLote = view.findViewById(R.id.gotero_num);
        String value=Integer.toString(listGoterosLote.get(position).getCantidad());
       txtGoterosLote.setText("N:"+value);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(position);

            }
        });
        return view;
    }
    private void showEditDialog(final int position){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_dialog);

        //Bind dialog views
        final EditText renameEdittext=(EditText)dialog.findViewById(R.id.rename_edittext);
        final Button btn_actualizar=(Button)dialog.findViewById(R.id.btn_actualizar_goteroLote);


        //Set clicked album name to rename edittext
        String value=Integer.toString(listGoterosLote.get(position).getCantidad());
        renameEdittext.setText(value);

        //When rename button is clicked, first rename edittext should be checked if it is empty
        //If it is not empty, data and listview item should be changed.
        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransaccionesBDD transaccion = new TransaccionesBDD(context);
                int nuevoValor=Integer.parseInt(String.valueOf(renameEdittext.getText()));
                //System.out.println("idGL: "+listGoterosLote.get(position).getId_lote_gotero()+" valor: "+nuevoValor);
                try {
                    transaccion.actualizarCantidaddeGoterosLotes(listGoterosLote.get(position).getId_lote_gotero(),nuevoValor);
                   // ArrayList<GoterosLotesModel> gotL=transaccion.consultaExisteGoterosLotes(listGoterosLote.get(position).getId_lote_gotero());
                    GoterosLotesModel gl=listGoterosLote.get(position);
                    listGoterosLote.clear();

                    listGoterosLote.addAll( transaccion.consultarGoterosLotesbyIDLote(gl.getId_lote()));

                    notifyDataSetChanged();
                    Toast.makeText(context,"Gotero Actualizado!",Toast.LENGTH_LONG).show();

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(context,"Error al Actualizar!",Toast.LENGTH_LONG).show();
                }

                dialog.dismiss();
            }
        });


        dialog.show();
    }
}

