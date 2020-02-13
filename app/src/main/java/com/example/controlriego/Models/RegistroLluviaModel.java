package com.example.controlriego.Models;

public class RegistroLluviaModel {
    private long id_registro_lluvia;
    private long id_finca;
    private String fecha_lluvia;
    private double milimetro_cubico;
    private String estado;
    private long id_usu_create;
    private String fecha_creacion;
    private long id_usu_update;
    private String fecha_update;
    private String estado_sinc;

    public RegistroLluviaModel(){}

    public RegistroLluviaModel(long id_registro_lluvia, String fecha_lluvia) {
        this.id_registro_lluvia = id_registro_lluvia;
        this.fecha_lluvia = fecha_lluvia;
    }

    public RegistroLluviaModel(long id_finca, String fecha_lluvia, double milimetro_cubico, String estado, long id_usu_create, String fecha_creacion, long id_usu_update, String fecha_update, String estado_sinc) {
        this.id_finca = id_finca;
        this.fecha_lluvia = fecha_lluvia;
        this.milimetro_cubico = milimetro_cubico;
        this.estado = estado;
        this.id_usu_create = id_usu_create;
        this.fecha_creacion = fecha_creacion;
        this.id_usu_update = id_usu_update;
        this.fecha_update = fecha_update;
        this.estado_sinc = estado_sinc;
    }

    public long getId_registro_lluvia() {
        return id_registro_lluvia;
    }

    public void setId_registro_lluvia(long id_registro_lluvia) {
        this.id_registro_lluvia = id_registro_lluvia;
    }

    public long getId_finca() {
        return id_finca;
    }

    public void setId_finca(long id_finca) {
        this.id_finca = id_finca;
    }

    public String getFecha_lluvia() {
        return fecha_lluvia;
    }

    public void setFecha_lluvia(String fecha_lluvia) {
        this.fecha_lluvia = fecha_lluvia;
    }

    public double getMilimetro_cubico() {
        return milimetro_cubico;
    }

    public void setMilimetro_cubico(double milimetro_cubico) {
        this.milimetro_cubico = milimetro_cubico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getId_usu_create() {
        return id_usu_create;
    }

    public void setId_usu_create(long id_usu_create) {
        this.id_usu_create = id_usu_create;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public long getId_usu_update() {
        return id_usu_update;
    }

    public void setId_usu_update(long id_usu_update) {
        this.id_usu_update = id_usu_update;
    }

    public String getFecha_update() {
        return fecha_update;
    }

    public void setFecha_update(String fecha_update) {
        this.fecha_update = fecha_update;
    }

    public String getEstado_sinc() {
        return estado_sinc;
    }

    public void setEstado_sinc(String estado_sinc) {
        this.estado_sinc = estado_sinc;
    }
}
