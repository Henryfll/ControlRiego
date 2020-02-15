package com.example.controlriego;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    //final String CREAR_TABLA_USUARIO="CREATE TABLE tb_Usuario (id INTEGER primary key autoincrement, usuario TEXT, contrasena TEXT)";

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TABLA USUARIO
        db.execSQL("create table usuarios(id integer primary key autoincrement,usuario text,contrasena text, estado_conexion INTEGER)");
        db.execSQL("insert into usuarios (usuario, contrasena, estado_conexion) values('ini','ini','0')");
        //CREACION DE TABLAS PARA WEB SERVICE
        db.execSQL("create table fincas(id_finca integer,nombre text,descripcion text, imagen text)");
        db.execSQL("create table lotes(id_lote integer,id_finca integer,nombre text, descripcion text, estado_gotero text)");
        db.execSQL("create table goteros(id_gotero integer,descripcion text,litro_hora real, estado text)");
        db.execSQL("create table goteroslotes(id_lote_gotero integer,id_lote integer,id_gotero integer, cantidad int, estado_sinc text)");
        //CREACION DE TABLAS INTERNAS
        db.execSQL("create table registro_lluvia(id_registro_lluvia integer primary key autoincrement, id_finca integer, fecha_lluvia text, milimetro_cubico real, estado text, id_usu_create integer, fecha_creacion text, id_usu_update integer, fecha_update text, estado_sinc text)");
        db.execSQL("create table riego(id_riego integer primary key autoincrement, id_lote integer, fecha_inicio text, fecha_fin text, estado text, id_usu_create integer, fecha_create text, id_usu_update integer, fecha_update text, estado_sinc text)");
        db.execSQL("create table detalleriego(id_detalle_riego integer primary key autoincrement, id_riego integer, id_lote_gotero integer, cantidad integer, estado text, id_usu_create integer, fecha_create text, id_usu_update integer, fecha_update text, estado_sinc text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //TABLA USUARIO
        db.execSQL("create table usuarios(id integer primary key autoincrement,usuario text,contrasena text, estado_conexion INTEGER)");
        db.execSQL("insert into usuarios (usuario, contrasena, estado_conexion) values('ini','ini','0')");
        //CREACION DE TABLAS PARA WEB SERVICE
        db.execSQL("create table fincas(id_finca integer,nombre text,descripcion text, imagen text)");
        db.execSQL("create table lotes(id_lote integer,id_finca integer,nombre text, descripcion text, estado_gotero text)");
        db.execSQL("create table goteros(id_gotero integer,descripcion text,litro_hora real, estado text)");
        db.execSQL("create table goteroslotes(id_lote_gotero integer,id_lote integer,id_gotero integer, cantidad int, estado_sinc text)");
        //CREACION DE TABLAS INTERNAS
        db.execSQL("create table registro_lluvia(id_registro_lluvia integer primary key autoincrement, id_finca integer, fecha_lluvia text, milimetro_cubico real, estado text, id_usu_create integer, fecha_creacion text, id_usu_update integer, fecha_update text, estado_sinc text)");
        db.execSQL("create table riego(id_riego integer primary key autoincrement, id_lote integer, fecha_inicio text, fecha_fin text, estado text, id_usu_create integer, fecha_create text, id_usu_update integer, fecha_update text, estado_sinc text)");
        db.execSQL("create table detalleriego(id_detalle_riego integer primary key autoincrement, id_riego integer, id_lote_gotero integer, cantidad integer, estado text, id_usu_create integer, fecha_create text, id_usu_update integer, fecha_update text, estado_sinc text)");
    }
}
