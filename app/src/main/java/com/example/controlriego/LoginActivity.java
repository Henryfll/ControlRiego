package com.example.controlriego;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    //creamos la variables locales
    EditText txtUsuario,txtContrasena;
    Button btnLogin;
    ArrayList<Usuario> usuariosConsulta;
    UsuarioController usuarioController;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    //metodo de ingreso
    public void ingresar(View v){
        String usuario=txtUsuario.getText().toString();
        String contrasena=txtContrasena.getText().toString();
        usuariosConsulta = new ArrayList<Usuario>();
        usuarioController = new UsuarioController(this);
        //fila=db.rawQuery("select usuario,contrasena from usuarios where usuario='"+usuario+"' and contrasena='"+contrasena+"'",null);
        usuariosConsulta = usuarioController.obtenerUsuarios(usuario, contrasena);
                //preguntamos si el cursor tiene algun valor almacenado
           //preguntamos si los datos ingresados son iguales
        if (usuariosConsulta.size()>0){
            //si son iguales entonces vamos a otra ventana
            //Menu es una nueva actividad empty
            Intent ven=new Intent(this,LoginActivity.class);
            startActivity(ven);
            //limpiamos las las cajas de texto
            txtUsuario.setText("");
            txtContrasena.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(),"Usuario incorrecto", Toast.LENGTH_LONG).show();
        }

    }

}
