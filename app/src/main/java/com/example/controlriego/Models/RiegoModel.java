package com.example.controlriego.Models;

public class RiegoModel {

    private long id_riego;
    private long id_lote;
    private String fecha_inicio;
    private String fecha_fin;
    private long estado;
    private long id_usu_create;
    private String fecha_create;
    private long id_usu_update;
    private String fecha_update;
    private long estado_sinc;

    public RiegoModel(){}

    public RiegoModel(long id_riego, long id_lote, String fecha_inicio, String fecha_fin, long estado, long id_usu_create, String fecha_create, long id_usu_update, String fecha_update, long estado_sinc) {
        this.id_riego = id_riego;
        this.id_lote = id_lote;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
        this.id_usu_create = id_usu_create;
        this.fecha_create = fecha_create;
        this.id_usu_update = id_usu_update;
        this.fecha_update = fecha_update;
        this.estado_sinc = estado_sinc;
    }

    public long getId_riego() {
        return id_riego;
    }

    public void setId_riego(long id_riego) {
        this.id_riego = id_riego;
    }

    public long getId_lote() {
        return id_lote;
    }

    public void setId_lote(long id_lote) {
        this.id_lote = id_lote;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public long getEstado() {
        return estado;
    }

    public void setEstado(long estado) {
        this.estado = estado;
    }

    public long getId_usu_create() {
        return id_usu_create;
    }

    public void setId_usu_create(long id_usu_create) {
        this.id_usu_create = id_usu_create;
    }

    public String getFecha_create() {
        return fecha_create;
    }

    public void setFecha_create(String fecha_create) {
        this.fecha_create = fecha_create;
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

    public long getEstado_sinc() {
        return estado_sinc;
    }

    public void setEstado_sinc(long estado_sinc) {
        this.estado_sinc = estado_sinc;
    }
}
