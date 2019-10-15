package com.example.controlriego;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    final String CREAR_TABLA_USUARIO="CREATE TABLE tb_Usuario (id INTEGER primary key autoincrement, usuario TEXT, contrasena TEXT)";

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(id integer primary key autoincrement,usuario text,contrasena text)");
        db.execSQL("insert into usuarios (usuario, contrasena) values('admin','admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("create table usuarios(id integer primary key autoincrement,usuario text,contrasena text)");
        db.execSQL("insert into usuarios (usuario, contrasena) values('admin','admin')");
    }
}
