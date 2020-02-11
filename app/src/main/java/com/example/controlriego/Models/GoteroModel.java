package com.example.controlriego.Models;

public class GoteroModel {
    private long id_gotero; // El ID de la B
    private String descripcion;
    private double litro_hora;
    private String estado;

    public GoteroModel(long id_gotero, String descripcion, double litro_hora, String estado) {
        this.id_gotero = id_gotero;
        this.descripcion = descripcion;
        this.litro_hora = litro_hora;
        this.estado = estado;
    }

    public long getId_gotero() {
        return id_gotero;
    }

    public void setId_gotero(long id_gotero) {
        this.id_gotero = id_gotero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLitro_hora() {
        return litro_hora;
    }

    public void setLitro_hora(double litro_hora) {
        this.litro_hora = litro_hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
