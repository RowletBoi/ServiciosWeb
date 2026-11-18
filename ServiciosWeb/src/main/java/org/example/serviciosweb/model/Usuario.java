package org.example.serviciosweb.model;

public class Usuario {

    private int id;
    private String nombre;
    private String username;
    private String correo;
    private String telefono;
    private String ciudad;
    private String empresa;

    public Usuario(){

    }
    public Usuario(int id, String nombre, String username, String email, String telefono, String ciudad, String empresa) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.correo = email;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}

