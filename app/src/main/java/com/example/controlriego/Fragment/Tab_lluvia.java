package com.example.controlriego.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlriego.ControlActivity;
import com.example.controlriego.Models.RegistroLluviaModel;
import com.example.controlriego.R;
import com.example.controlriego.TransaccionesBDD;

import java.util.Calendar;


public class Tab_lluvia extends Fragment {

    DatePickerDialog picker;
    EditText txt_fecha;
    EditText txt_milimetros_cubicos;
    Button btn_Guardar;
    public long idPropiedad;

    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tab_lluvia, container, false);
        txt_fecha=(EditText) view.findViewById(R.id.fecha_lluvia);
        txt_milimetros_cubicos=(EditText) view.findViewById(R.id.milimetros_cubicos);
        btn_Guardar=(Button) view.findViewById(R.id.btn_guardarLluvia);
        txt_fecha.setInputType(InputType.TYPE_NULL);
        txt_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txt_fecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        btn_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransaccionesBDD trans = new TransaccionesBDD(getContext());
                RegistroLluviaModel registro = new RegistroLluviaModel();
                registro.setId_finca(idPropiedad);
                registro.setFecha_lluvia(txt_fecha.getText().toString());
                registro.setMilimetro_cubico(Double.parseDouble(txt_milimetros_cubicos.getText().toString()));
                registro.setEstado("1");
                registro.setId_usu_create(1);
                final Calendar fechaActual = Calendar.getInstance();
                int day = fechaActual.get(Calendar.DAY_OF_MONTH);
                int month = fechaActual.get(Calendar.MONTH);
                int year = fechaActual.get(Calendar.YEAR);
                registro.setFecha_creacion(""+day + "/" + (month + 1) + "/" + year);
                registro.setEstado_sinc("1");
                if(trans.registrarLluvia(registro)){
                    txt_fecha.setText("");
                    txt_milimetros_cubicos.setText("");
                    Toast.makeText(getContext(),"Registro de lluvia exitoso",Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(getContext(),"Error la fecha ingresada ya existe",Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }


}
