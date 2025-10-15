package com.example.E_commerce.models;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idCliente;
    private String nombre;
    private String direccion;
    private int telefono;

    public Clientes() {
    }

    public Clientes(Long idCliente, String nombre, String direccion, int telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
/*
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
    private Usuarios usuario;*/
    @ManyToOne
    @JoinColumn(name="idUser")

    private UsersModel user;
    public UsersModel getUser() {
        return user;
    }
    public void setUser(UsersModel user) {
        this.user = user;
    }
}
