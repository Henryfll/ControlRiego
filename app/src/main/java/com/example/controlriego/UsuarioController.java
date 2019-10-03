package com.example.controlriego;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UsuarioController {
    private ConexionSQLiteHelper ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "usuario";

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
        String[] columnasAConsultar = {"id", "usuario", "contrasena"};
        Cursor cursor=baseDeDatos.rawQuery("select usuario,contrasena from usuarios where usuario='"+usuario+"' and contrasena='"+contrasena+"'",null);

        /*Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );*/

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
            System.out.println("Valores base " +usuarioObtenidoDeBD);
            System.out.println("Valores base " +contrasenaObtenidaDeBD);
            Usuario userObtenidoDeBD = new Usuario(usuarioObtenidoDeBD, contrasenaObtenidaDeBD);
            usuarios.add(userObtenidoDeBD);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de mascotas :)
        cursor.close();
        return usuarios;

    }
}
