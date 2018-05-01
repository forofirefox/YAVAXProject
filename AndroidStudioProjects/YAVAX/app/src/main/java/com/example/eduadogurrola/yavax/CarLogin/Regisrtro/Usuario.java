package com.example.eduadogurrola.yavax.CarLogin.Regisrtro;

/**
 * Created by Eduado Gurrola on 20/04/2018.
 */

public class Usuario {
    private String nombre, apellidos,usuario,contra,correo;
    private int telefono;

    public String getCorreo() {
        return correo;
    }

    public Usuario(String nombre, String apellidos, String usuario, String contra, String correo, int telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.correo=correo;
        this.contra = contra;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
