package com.example.E_commerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Entity
public class PedidosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idPedido;
    private LocalDateTime fechaPedido;
    private String estado;
    private Long idCliente;

    public PedidosModel() {
    }

    public PedidosModel(LocalDateTime fechaPedido, String estado, Long idCliente) {
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    public Long getIdPedido() {return idPedido;}

    public void setIdPedido(Long id) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getfechaPedido() {
        return fechaPedido;
    }

    public void setfechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
}
