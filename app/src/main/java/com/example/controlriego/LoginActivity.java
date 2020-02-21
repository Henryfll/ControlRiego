package com.example.controlriego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.controlriego.Controllers.UsuarioController;
import com.example.controlriego.Models.DetalleRiegoModel;
import com.example.controlriego.Models.FincaModel;
import com.example.controlriego.Models.GoteroModel;
import com.example.controlriego.Models.GoterosLotesModel;
import com.example.controlriego.Models.LoteModel;
import com.example.controlriego.Models.RiegoModel;
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
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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
            if(Respuesta!=null){
                UsuarioRespuesta=Respuesta.get(0);
            }
            UsuarioController usuarioRegistro = new UsuarioController(this);
            usuarioRegistro.RegistrarNuevoUsuario(username,password);
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
            /*for (GoterosLotesModel item: goterosLote) {
                System.out.println("Nombre: "+item.getId_lote_gotero());
                System.out.println("NombreEs: "+item.getEstado_sinc());
            }*/
            System.out.println("fincas "+fincas.size()+"lotes "+lotes.size()+"goteros "+goteros.size()+"goteroLote "+goterosLote.size());
            DescargarData(fincas,lotes,goteros,goterosLote);

            //Prueba de Insert Riego y detalle-riego
            RiegoModel riego1 = new RiegoModel();
            riego1.setId_lote(1);
            riego1.setFecha_inicio("15/2/2020");
            riego1.setFecha_fin("16/2/2020");
            riego1.setEstado(1);
            riego1.setId_usu_create(1);
            riego1.setFecha_create("15/2/2020");
            riego1.setEstado_sinc(0);

            RiegoModel riego2 = new RiegoModel();
            riego2.setId_lote(2);
            riego2.setFecha_inicio("17/2/2020");
            //riego2.setFecha_fin("18/2/2020");
            riego2.setEstado(1);
            riego2.setId_usu_create(1);
            riego2.setFecha_create("17/2/2020");
            riego2.setEstado_sinc(0);

            DetalleRiegoModel detaRiego1 = new DetalleRiegoModel();
            detaRiego1.setId_riego(1);
            detaRiego1.setId_lote_gotero(1);
            detaRiego1.setCantidad(2);
            detaRiego1.setEstado(1);
            detaRiego1.setId_usu_create(1);
            detaRiego1.setFecha_create("15/2/2020");
            detaRiego1.setEstado_sinc(0);

            DetalleRiegoModel detaRiego2 = new DetalleRiegoModel();
            detaRiego2.setId_riego(2);
            detaRiego2.setId_lote_gotero(2);
            detaRiego2.setCantidad(2);
            detaRiego2.setEstado(1);
            detaRiego2.setId_usu_create(1);
            detaRiego2.setFecha_create("17/2/2020");
            detaRiego2.setEstado_sinc(0);

            TransaccionesBDD tran = new TransaccionesBDD(this);
            tran.InsertarRiego(riego1);
            tran.InsertarRiego(riego2);
            tran.InsertarDetalleRiego(detaRiego1);
            tran.InsertarDetalleRiego(detaRiego2);
            System.out.println("Nombre: "+tran.consultaRiegobyIDLote(1).getId_riego());
            System.out.println("Nombre: "+tran.consultaRiegobyIDLote(2).getId_riego());
            //tran.actualizarFechaFinRiego(1, "19/2/2020");
            //Prueba desde base
            /*for (RiegoModel item: tran.consultaRiegos()) {
                System.out.println("Nombre: "+item.getId_riego());
                System.out.println("NombreEs: "+item.getFecha_fin());
            }
            for (DetalleRiegoModel item: tran.consultaDetalleRiegos()) {
                System.out.println("Nombre: "+item.getId_detalle_riego());
                System.out.println("NombreEs: "+item.getFecha_create());
            }*/
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
