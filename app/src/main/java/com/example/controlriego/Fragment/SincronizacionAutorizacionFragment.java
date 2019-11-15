package com.example.controlriego.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlriego.Controllers.UsuarioController;
import com.example.controlriego.MainActivity;
import com.example.controlriego.Models.Usuario;
import com.example.controlriego.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SincronizacionAutorizacionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SincronizacionAutorizacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SincronizacionAutorizacionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //creamos la variables locales
    EditText txtUsuario,txtContrasena;
    //Button btnConfirmar;
    ArrayList<Usuario> usuariosConsulta;
    UsuarioController usuarioController;

    private OnFragmentInteractionListener mListener;

    public SincronizacionAutorizacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SincronizacionAutorizacionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SincronizacionAutorizacionFragment newInstance(String param1, String param2) {
        SincronizacionAutorizacionFragment fragment = new SincronizacionAutorizacionFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sincronizacion, container, false);
        //emperejamos las variable con el xml
        txtUsuario= (EditText) view.findViewById(R.id.input_usuario);
        txtContrasena= (EditText) view.findViewById(R.id.input_contrasena);
        final Button btnConfirmar= (Button) view.findViewById(R.id.btn_confirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar(view);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //metodo de ingreso
    public void validar(View v){
        String usuario=txtUsuario.getText().toString();
        String contrasena=txtContrasena.getText().toString();
        usuariosConsulta = new ArrayList<Usuario>();
        usuarioController = new UsuarioController(getContext());
        usuariosConsulta = usuarioController.obtenerUsuarios(usuario, contrasena);
        //preguntamos si el cursor tiene algun valor almacenado
        //preguntamos si los datos ingresados son iguales
        if (usuariosConsulta.size()>0){
            SincronizacionBaseFragment sf=new SincronizacionBaseFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, sf).commit();
        }
        else{
            Toast.makeText(getContext(),"Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
        }

    }
}
