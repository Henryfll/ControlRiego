package com.example.controlriego.Models;

public class UsuarioDto {
    private long id_usuario; // El ID de la BD
    private long id_persona;
    private String identificacion;
    private String nombre;
    private String api_token;

    public UsuarioDto(long id_persona, String identificacion, String nombre, String api_token) {
        this.id_persona = id_persona;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.api_token = api_token;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public long getId_persona() {
        return id_persona;
    }

    public void setId_persona(long id_persona) {
        this.id_persona = id_persona;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
}
