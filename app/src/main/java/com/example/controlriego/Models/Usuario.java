package com.example.controlriego.Models;

public class Usuario {
    private long id; // El ID de la BD
    private String usuario;
    private String contrasena;
    private int estado_conexion;

    public Usuario(){}

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Usuario(String usuario, String contrasena, int estado_conexion) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estado_conexion = estado_conexion;
    }

    public Usuario(long id, String usuario, String contrasena) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getEstado_conexion() { return estado_conexion; }

    public void setEstado_conexion(int estado_conexion) { this.estado_conexion = estado_conexion; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "tb_Usuario{" +
                "usuario='" + usuario + '\'' +
                ", contrasena=" + contrasena +
                ", estado_conexion=" + estado_conexion +
                '}';
    }

}
