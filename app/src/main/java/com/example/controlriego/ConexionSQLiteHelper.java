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
        db.execSQL("insert into usuarios (usuario, contrasena, estado_conexion) values('ADMIN','ADMIN','0')");
        //CREACION DE TABLAS
        db.execSQL("create table fincas(id_finca integer primary key,nombre text,descripcion text, imagen text)");
        db.execSQL("create table lotes(id_lote integer,id_finca integer,nombre text, descripcion text)");
        db.execSQL("create table goteros(id_gotero integer,descripcion text,litro_hora real, estado text)");
        db.execSQL("create table goteroslotes(id_lote_gotero integer,id_lote integer,id_gotero integer, cantidad int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("create table usuarios(id integer primary key autoincrement,usuario text,contrasena text, estado_conexion INTEGER)");
        db.execSQL("insert into usuarios (usuario, contrasena, estado_conexion) values('admin','admin','0')");
        //CREACION DE TABLAS
        db.execSQL("create table fincas(id_lote_gotero integer,id_lote integer,id_gotero integer, cantidad int)");
    }
}
