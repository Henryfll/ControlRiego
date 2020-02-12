package com.example.controlriego;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.controlriego.Models.FincaModel;
import com.example.controlriego.Models.GoteroModel;
import com.example.controlriego.Models.GoterosLotesModel;
import com.example.controlriego.Models.LoteModel;

import java.util.ArrayList;

public class TransaccionesBDD {
    private ConexionSQLiteHelper ayudanteBaseDeDatos;
    private String TABLA_USUARIOS = "usuarios";
    private String TABLA_FINCAS = "fincas";
    private String TABLA_LOTES = "lotes";
    private String TABLA_GOTEROS = "goteros";
    private String TABLA_GOTEROSLOTE = "goteroslote";

    public TransaccionesBDD(Context contexto) {
        ayudanteBaseDeDatos = new ConexionSQLiteHelper(contexto,"bd_controlriego",null,1);
    }

    public void InsertarFincas(ArrayList<FincaModel> fincas) {
        SQLiteDatabase db = ayudanteBaseDeDatos.getWritableDatabase();
        for (FincaModel item: fincas) {
            if(consultaExisteFinca(item.getId_finca()).size()==0){
                db.execSQL("INSERT INTO fincas VALUES(" +
                        item.getId_finca() + ",'" +
                        item.getNombre() + "','" +
                        item.getDescripcion() + "','" +
                        item.getImagen() + "');");
            }
        }
    }

    public ArrayList<FincaModel> consultaExisteFinca(long id){
        ArrayList<FincaModel> fincasdeBDD = new ArrayList<FincaModel>();
        SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
        Cursor cursor=bd.rawQuery("select * from fincas where id_finca='"+id+"'", null);
        if (cursor == null) return fincasdeBDD;
        if (!cursor.moveToFirst()) return fincasdeBDD;

        do {
            FincaModel fincadeBDD = new FincaModel(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));

            fincasdeBDD.add(fincadeBDD);
        } while (cursor.moveToNext());
        cursor.close();
        return fincasdeBDD;
    }

    public ArrayList<FincaModel> consultarFincas(){
        ArrayList<FincaModel> fincasdeBDD = new ArrayList<FincaModel>();
        SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
        Cursor cursor=bd.rawQuery("select * from fincas ", null);

        if (cursor == null) return fincasdeBDD;
        if (!cursor.moveToFirst()) return fincasdeBDD;

        do {
            FincaModel fincadeBDD = new FincaModel(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));

            fincasdeBDD.add(fincadeBDD);
        } while (cursor.moveToNext());

        cursor.close();
        return fincasdeBDD;
    }

    public void InsertarLotes(ArrayList<LoteModel> lotes) {
        SQLiteDatabase db = ayudanteBaseDeDatos.getWritableDatabase();
        for (LoteModel item: lotes) {
            if(consultaExisteLote(item.getId_lote()).size()==0){
                db.execSQL("INSERT INTO lotes VALUES(" +
                        item.getId_lote() + ",'" +
                        item.getId_finca() + "','" +
                        item.getNombre() + "','" +
                        item.getDescripcion() + "');");
            }
        }
    }

    public ArrayList<LoteModel> consultaExisteLote(long id){
        ArrayList<LoteModel> lotesdeBDD = new ArrayList<LoteModel>();
        SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
        Cursor cursor=bd.rawQuery("select * from lotes where id_lote='"+id+"'", null);

        if (cursor == null) return lotesdeBDD;
        if (!cursor.moveToFirst()) return lotesdeBDD;

        do {
            LoteModel lotedeBDD = new LoteModel(cursor.getLong(0),
                    cursor.getLong(1),
                    cursor.getString(2),
                    cursor.getString(3));

            lotesdeBDD.add(lotedeBDD);
        } while (cursor.moveToNext());

        cursor.close();
        return lotesdeBDD;
    }

        public ArrayList<LoteModel> consultarLotesbyID(long id_propiedad){
        ArrayList<LoteModel> lotesdeBDD = new ArrayList<LoteModel>();
        SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
        Cursor cursor=bd.rawQuery("select * from lotes where id_finca='"+id_propiedad+"'", null);

        if (cursor == null) return lotesdeBDD;
        if (!cursor.moveToFirst()) return lotesdeBDD;

        do {
            LoteModel lotedeBDD = new LoteModel(cursor.getLong(0),
                    cursor.getLong(1),
                    cursor.getString(2),
                    cursor.getString(3));

            lotesdeBDD.add(lotedeBDD);
        } while (cursor.moveToNext());

        cursor.close();
        return lotesdeBDD;
    }

    public void InsertarGoteros(ArrayList<GoteroModel> goteros) {
        SQLiteDatabase db = ayudanteBaseDeDatos.getWritableDatabase();
        for (GoteroModel item: goteros) {
            if(consultaExisteGotero(item.getId_gotero()).size()==0){
                db.execSQL("INSERT INTO goteros VALUES(" +
                        item.getId_gotero() + ",'" +
                        item.getDescripcion() + "','" +
                        item.getLitro_hora() + "','" +
                        item.getEstado() + "');");
            }
        }
    }

    public ArrayList<GoteroModel> consultaExisteGotero(long id){
        ArrayList<GoteroModel> goterosdeBDD = new ArrayList<GoteroModel>();
        SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
        Cursor cursor=bd.rawQuery("select * from goteros where id_gotero='"+id+"'", null);
        if (cursor == null) return goterosdeBDD;
        if (!cursor.moveToFirst()) return goterosdeBDD;

        do {
            GoteroModel goterodeBDD = new GoteroModel(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3));

            goterosdeBDD.add(goterodeBDD);
        } while (cursor.moveToNext());
        cursor.close();
        return goterosdeBDD;
    }

    public ArrayList<GoteroModel> consultarGoterosbyID(long id_gotero){
        ArrayList<GoteroModel> goterosdeBDD = new ArrayList<GoteroModel>();
        SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
        Cursor cursor=bd.rawQuery("select * from goteros where id_gotero='"+id_gotero+"' ", null);
        if (cursor == null) return goterosdeBDD;
        if (!cursor.moveToFirst()) return goterosdeBDD;

        do {
            GoteroModel goterodeBDD = new GoteroModel(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getString(3));

            goterosdeBDD.add(goterodeBDD);
        } while (cursor.moveToNext());
        cursor.close();
        return goterosdeBDD;
    }

    public void InsertarGoterosLotes(ArrayList<GoterosLotesModel> goteroslotes) {
        SQLiteDatabase db = ayudanteBaseDeDatos.getWritableDatabase();
        for (GoterosLotesModel item: goteroslotes) {
            if(consultaExisteFinca(item.getId_lote_gotero()).size()==0){
                db.execSQL("INSERT INTO goteroslotes VALUES(" +
                        item.getId_lote_gotero() + ",'" +
                        item.getId_lote() + "','" +
                        item.getId_gotero() + "','" +
                        item.getCantidad() + "');");
            }
        }
    }

    public ArrayList<GoterosLotesModel> consultaExisteGoterosLotes(long id){
        ArrayList<GoterosLotesModel> listadeBDD = new ArrayList<GoterosLotesModel>();
        SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
        Cursor cursor=bd.rawQuery("select * from goteroslotes where id_lote_gotero='"+id+"'", null);
        if (cursor == null) return listadeBDD;
        if (!cursor.moveToFirst()) return listadeBDD;

        do {
            GoterosLotesModel goteroslotesBDD = new GoterosLotesModel(cursor.getLong(0),
                    cursor.getLong(1),
                    cursor.getLong(2),
                    cursor.getInt(3));

            listadeBDD.add(goteroslotesBDD);
        } while (cursor.moveToNext());

        cursor.close();
        return listadeBDD;
    }

    public ArrayList<GoterosLotesModel> consultarGoterosLotesbyIDLote(long id_lote){
        ArrayList<GoterosLotesModel> listadeBDD = new ArrayList<GoterosLotesModel>();
        SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
        Cursor cursor=bd.rawQuery("select * from goteroslotes where id_lote='"+id_lote+"' ", null);
        if (cursor == null) return listadeBDD;
        if (!cursor.moveToFirst()) return listadeBDD;

        do {
            GoterosLotesModel goteroslotesBDD = new GoterosLotesModel(cursor.getLong(0),
                    cursor.getLong(1),
                    cursor.getLong(2),
                    cursor.getInt(3));

            listadeBDD.add(goteroslotesBDD);
        } while (cursor.moveToNext());

        cursor.close();
        return listadeBDD;
    }


}
