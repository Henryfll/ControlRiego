package com.example.controlriego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlriego.Controllers.UsuarioController;
import com.example.controlriego.Models.FincaModel;
import com.example.controlriego.Models.GoteroModel;
import com.example.controlriego.Models.GoterosLotesModel;
import com.example.controlriego.Models.LoteModel;
import com.example.controlriego.Models.Usuario;
import com.example.controlriego.Models.UsuarioDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LoginActivity extends AppCompatActivity{


    //creamos la variables locales
    EditText txtUsuario,txtContrasena;
    Button btnLogin;
    ArrayList<Usuario> usuariosConsulta;
    UsuarioController usuarioController;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UsuarioController usuarioController = new UsuarioController(this);
        String usuarioConsultado=usuarioController.obtenerUsuarioConectado().getUsuario();

        if(usuarioConsultado!=null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {

            setContentView(R.layout.activity_login);
            //emperejamos las variable con el xml
            txtUsuario= (EditText) findViewById(R.id.input_usuario);
            txtContrasena= (EditText) findViewById(R.id.input_contrasena);
            btnLogin= (Button) findViewById(R.id.btn_login);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ingresar(view);
                }
            });
        }
    }

    //metodo de ingreso
    public void ingresar(View v){
        String usuario=txtUsuario.getText().toString();
        String contrasena=txtContrasena.getText().toString();
        consumirServicio(usuario,contrasena);
        //DescargarData(); listas de consumir servicio

        usuariosConsulta = new ArrayList<Usuario>();
        usuarioController = new UsuarioController(this);
        usuariosConsulta = usuarioController.obtenerUsuarios(usuario, contrasena);
           //preguntamos si los datos ingresados son iguales
        if (usuariosConsulta.size()>0){
            usuarioController.ActualizarUsuarioConectado(usuario, 1);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Usuario incorrecto", Toast.LENGTH_LONG).show();
        }

    }
    public void consumirServicio(String username,String password){
        // ahora ejecutaremos el hilo creado
        String resultado=null;
        UsuarioDto UsuarioRespuesta=null;
        ServicioTask servicioTask= new ServicioTask(this,"https://api.ofcorp.com.ec/users/login",username,password);
        try {
            resultado =servicioTask.execute().get(2000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        if (resultado=="406"){

        }else{
            Gson gson = new Gson();
            Type type = new TypeToken< ArrayList<UsuarioDto>>(){}.getType();
            ArrayList<UsuarioDto>Respuesta=gson.fromJson(resultado, type);
            UsuarioRespuesta=Respuesta.get(0);
          /*  Usuario usuario= new Usuario();
            usuario.setId(UsuarioRespuesta.getId_usuario());
            usuario.setUsuario(username);
            usuario.setContrasena(password);
            System.out.println("usuario "+usuario.toString());
            usuarioController.guardarCambios(usuario);*/
        }
        if(UsuarioRespuesta!=null){

            ArrayList<FincaModel> fincas=new ArrayList<FincaModel>();
            ArrayList<LoteModel> lotes=new ArrayList<LoteModel>();
            ArrayList<GoteroModel> goteros=new ArrayList<GoteroModel>();
            ArrayList<GoterosLotesModel> goterosLote=new ArrayList<GoterosLotesModel>();
        //Fincas
            ServiciosPost servicioTaskFincas= new ServiciosPost(this,"https://api.ofcorp.com.ec/fincas",UsuarioRespuesta.getApi_token(), (int) UsuarioRespuesta.getId_usuario());
            try {
                resultado=null;
                resultado =servicioTaskFincas.execute().get(1000, TimeUnit.MILLISECONDS);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            if(resultado!=null){
                Gson gson = new Gson();
                Type founderListType = new TypeToken<ArrayList<FincaModel>>(){}.getType();
                fincas=gson.fromJson(resultado , founderListType);
            }
         //Lotes
            ServiciosPost servicioTaskLotes= new ServiciosPost(this,"https://api.ofcorp.com.ec/lotes",UsuarioRespuesta.getApi_token(), (int) UsuarioRespuesta.getId_usuario());
            try {
                resultado=null;
                resultado =servicioTaskLotes.execute().get(1000, TimeUnit.MILLISECONDS);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            if(resultado!=null){
                Gson gson = new Gson();
                Type founderListType = new TypeToken<ArrayList<LoteModel>>(){}.getType();
                lotes=gson.fromJson(resultado , founderListType);
            }
         //Goteros
            ServiciosPost servicioTaskGoteros= new ServiciosPost(this,"https://api.ofcorp.com.ec/goteros",UsuarioRespuesta.getApi_token(), (int) UsuarioRespuesta.getId_usuario());
            try {
                resultado=null;
                resultado =servicioTaskGoteros.execute().get(1000, TimeUnit.MILLISECONDS);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            if(resultado!=null){
                Gson gson = new Gson();
                Type founderListType = new TypeToken<ArrayList<GoteroModel>>(){}.getType();
                goteros=gson.fromJson(resultado , founderListType);
            }
         //Goteros por Lote
            ServiciosPost servicioTaskGoteroLote= new ServiciosPost(this,"https://api.ofcorp.com.ec/goteroslote",UsuarioRespuesta.getApi_token(), (int) UsuarioRespuesta.getId_usuario());
            try {
                resultado=null;
                resultado =servicioTaskGoteroLote.execute().get(1000, TimeUnit.MILLISECONDS);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            if(resultado!=null){
                Gson gson = new Gson();
                Type founderListType = new TypeToken<ArrayList<GoterosLotesModel>>(){}.getType();
                goterosLote=gson.fromJson(resultado , founderListType);
            }
            //Guardar en la base
            System.out.println("fincas "+fincas.size()+"lotes "+lotes.size()+"goteros "+goteros.size()+"goteroLote "+goterosLote.size());
            DescargarData(fincas,lotes,goteros,goterosLote);
        }



    }

    public void DescargarData(ArrayList<FincaModel> fincas, ArrayList<LoteModel> lotes, ArrayList<GoteroModel> goteros, ArrayList<GoterosLotesModel> goterosLotes){
        TransaccionesBDD transaccionesBDD = new TransaccionesBDD(getApplicationContext());
        transaccionesBDD.InsertarFincas(fincas);
        transaccionesBDD.InsertarLotes(lotes);
        transaccionesBDD.InsertarGoteros(goteros);
        transaccionesBDD.InsertarGoterosLotes(goterosLotes);
    }


}
