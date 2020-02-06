package com.example.controlriego.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.controlriego.Adapters.PropiedadAdapter;
import com.example.controlriego.R;
import com.example.controlriego.Models.PropiedadesModel;
import java.util.ArrayList;
import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Build;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaPropiedadesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaPropiedadesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaPropiedadesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private String FRAGMENT_TAG = "ListaAtractivos"; // TAG de identificacion de Fragmento

    private ListView listView; //  listview donde se cargaran la lista de atractivos

    public ProgressBar progressBar; // Spinner indicador de la carga de atractivos

    private SwipeRefreshLayout swipeContainer; // permite recargar los datos

    public ArrayList<PropiedadesModel> listaPropiedades = new ArrayList<>(); // Array de atractivos

    private OnFragmentInteractionListener mListener;

    public ListaPropiedadesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaPropiedadesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaPropiedadesFragment newInstance(String param1, String param2) {
        ListaPropiedadesFragment fragment = new ListaPropiedadesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_propiedades, container, false);
        listView = (ListView) view.findViewById(R.id.listViewAtractivos);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar_atractivos);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.srlContainer);

        swipeContainer.setOnRefreshListener(this);
        //Podemos espeficar si queremos, un patron de colores diferente al patrón por defecto.
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Update data in ListView
                        ConsultarPropiedades();
                        swipeContainer.setRefreshing(false);
                    }
                }, 3000);

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ConsultarPropiedades();

    }
    public void ConsultarPropiedades(){
        PropiedadesModel obj=new PropiedadesModel();
        obj.setId(0);
        obj.setCategoria("Finca");
        obj.setNombre("Finca Prueba");
        obj.setDescripcion("Finca para el control de riego de agua");
        obj.setPathImagen("drawable/ic_picture");
        listaPropiedades.add(obj);
        PropiedadesModel obj1=new PropiedadesModel();
        obj1.setId(1);
        obj1.setCategoria("Finca");
        obj1.setNombre("Finca Prueba");
        obj1.setDescripcion("Finca para el control de riego de agua");
        obj1.setPathImagen("drawable/ic_picture");
        listaPropiedades.add(obj1);
        if(getContext()!= null) {
            listView.setAdapter(new PropiedadAdapter(getContext(),listaPropiedades));
        }
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

    @Override
    public void onRefresh() {

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
}
