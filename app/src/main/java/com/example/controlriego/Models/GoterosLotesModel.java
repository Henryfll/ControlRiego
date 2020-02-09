package com.example.controlriego.Models;

public class GoterosLotesModel {
    private long id_lote_gotero;
    private long id_lote;
    private long id_gotero;
    private int cantidad;

    public GoterosLotesModel(){}

    public GoterosLotesModel(long id_lote_gotero, long id_lote, long id_gotero, int cantidad) {
        this.id_lote_gotero = id_lote_gotero;
        this.id_lote = id_lote;
        this.id_gotero = id_gotero;
        this.cantidad = cantidad;
    }

    public long getId_lote_gotero() {
        return id_lote_gotero;
    }

    public void setId_lote_gotero(long id_lote_gotero) {
        this.id_lote_gotero = id_lote_gotero;
    }

    public long getId_lote() {
        return id_lote;
    }

    public void setId_lote(long id_lote) {
        this.id_lote = id_lote;
    }

    public long getId_gotero() {
        return id_gotero;
    }

    public void setId_gotero(long id_gotero) {
        this.id_gotero = id_gotero;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
