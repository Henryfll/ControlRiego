package com.example.controlriego.Models;

public class LoteModel {

    private long id_lote; // El ID de la BD
    private long id_finca;
    private String nombre;
    private String descripcion;

    public LoteModel(long id_lote, long id_finca, String nombre, String descripcion) {
        this.id_lote = id_lote;
        this.id_finca = id_finca;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public LoteModel(){

}


    public long getId_lote() {
        return id_lote;
    }

    public void setId_lote(long id_lote) {
        this.id_lote = id_lote;
    }

    public long getId_finca() {
        return id_finca;
    }

    public void setId_finca(long id_finca) {
        this.id_finca = id_finca;
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
