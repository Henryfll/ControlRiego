package com.example.controlriego.Models;

<<<<<<< HEAD
import java.util.ArrayList;

public class FincaModel{
=======
public class FincaModel {
>>>>>>> origin/master
    private long id_finca;
    private String nombre;
    private String descripcion;
    private String imagen;

    public FincaModel(){}

    public FincaModel(long id_finca, String nombre, String descripcion, String imagen) {
        this.id_finca = id_finca;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
