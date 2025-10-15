package com.example.E_commerce.models;


import jakarta.persistence.*;

@Entity
public class Comerciantes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComerciante;
    private String nombre;
    private String negocio;

    public Comerciantes() {
    }

    public Comerciantes(Long idComerciante, String nombre, String negocio) {
        this.idComerciante = idComerciante;
        this.nombre = nombre;
        this.negocio = negocio;
    }

    public Long getIdComerciante() {
        return idComerciante;
    }

    public void setIdComerciante(Long idComerciante) {
        this.idComerciante = idComerciante;
    }

    public String getNegocio() {
        return negocio;
    }

    public void setNegocio(String negocio) {
        this.negocio = negocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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
