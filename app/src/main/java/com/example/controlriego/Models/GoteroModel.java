package com.example.controlriego.Models;

public class GoteroModel {
    private long got_codigo; // El ID de la B
    private String descripcion;
    private double litro_hora;
    private Character estado;

    public GoteroModel(long got_codigo, String descripcion, double litro_hora, Character estado) {
        this.got_codigo = got_codigo;
        this.descripcion = descripcion;
        this.litro_hora = litro_hora;
        this.estado = estado;
    }

    public long getGot_codigo() {
        return got_codigo;
    }

    public void setGot_codigo(long got_codigo) {
        this.got_codigo = got_codigo;
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

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }
}
