package com.example.controlriego.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.controlriego.ConexionSQLiteHelper;
import com.example.controlriego.Models.Usuario;

import java.util.ArrayList;

public class UsuarioController {
    private ConexionSQLiteHelper ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "usuarios";

    public UsuarioController(Context contexto) {
        ayudanteBaseDeDatos = new ConexionSQLiteHelper(contexto,"bd_controlriego",null,1);
    }


    public int eliminarUsuario(Usuario usuario) {

        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(usuario.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevoUsuario(Usuario usuario) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("usuario", usuario.getUsuario());
        valoresParaInsertar.put("contrasena", usuario.getContrasena());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(Usuario usuarioEditado) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("usuario", usuarioEditado.getUsuario());
        valoresParaActualizar.put("contrasena", usuarioEditado.getContrasena());
        // where id...
        String campoParaActualizar = "id = ?";
        // ... = idMascota
        String[] argumentosParaActualizar = {String.valueOf(usuarioEditado.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Usuario> obtenerUsuarios(String usuario, String contrasena) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT nombre, edad, id
        String[] columnasAConsultar = {"id", "usuario", "contrasena" };
        Cursor cursor=baseDeDatos.rawQuery("select usuario,contrasena from usuarios where usuario='"+usuario+"' and contrasena='"+contrasena+"'",null);


        if (cursor == null) {
            /*
                Salimos aquí porque hubo un error, regresar
                lista vacía
             */
            return usuarios;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return usuarios;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de mascotas
        do {
            // El 0 es el número de la columna, como seleccionamos
            String usuarioObtenidoDeBD = cursor.getString(0);
            String contrasenaObtenidaDeBD = cursor.getString(1);
            Usuario userObtenidoDeBD = new Usuario(usuarioObtenidoDeBD, contrasenaObtenidaDeBD);
            usuarios.add(userObtenidoDeBD);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista:)
        cursor.close();
        return usuarios;

    }

    public Usuario obtenerUsuarioConectado() {
        Usuario usuario_con = new Usuario();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnasAConsultar = {"id", "usuario", "contrasena" };
        Cursor cursor=baseDeDatos.rawQuery("select usuario,contrasena from usuarios where estado_conexion=1",null);


        if (cursor == null) {
            return usuario_con;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return usuario_con;

        do {
            // El 0 es el número de la columna, como seleccionamos
            String usuarioObtenidoDeBD = cursor.getString(0);
            String contrasenaObtenidaDeBD = cursor.getString(1);
            usuario_con = new Usuario(usuarioObtenidoDeBD, contrasenaObtenidaDeBD);

        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista:)
        cursor.close();
        return usuario_con;

    }

    public void ActualizarUsuarioConectado(String usuarioEditado, int estado) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        /*ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("estado_conexion", estado);
        String campoParaActualizar = "usuario = "+usuarioEditado;
        baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, null);*/
        if(estado ==1)
            baseDeDatos.execSQL("UPDATE '"+NOMBRE_TABLA+"' SET estado_conexion='"+estado+"' WHERE usuario='"+usuarioEditado+"' ");
        else
            baseDeDatos.execSQL("UPDATE '"+NOMBRE_TABLA+"' SET estado_conexion='"+estado+"' WHERE usuario!='"+usuarioEditado+"' ");
    }
}
