package com.example.controlriego.Models;

public class LoteModel {

    private long lot_codigo; // El ID de la BD
    private long fin_codigo;
    private String nombre;
    private String descripcion;

    public LoteModel(long lot_codigo, long fin_codigo, String nombre, String descripcion) {
        this.lot_codigo = lot_codigo;
        this.fin_codigo = fin_codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
public LoteModel(){

}


    public long getLot_codigo() {
        return lot_codigo;
    }

    public void setLot_codigo(long lot_codigo) {
        this.lot_codigo = lot_codigo;
    }

    public long getFin_codigo() {
        return fin_codigo;
    }

    public void setFin_codigo(long fin_codigo) {
        this.fin_codigo = fin_codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
