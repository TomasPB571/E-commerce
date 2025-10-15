package com.example.E_commerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class VentasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    private Long idPedido;
    private Long idProducto;
    private Long cantidad;
    private BigDecimal total;

    public VentasModel() {

    }

    public VentasModel(Long idPedido, Long idProducto, Long cantidad, BigDecimal total) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Long getIdVenta() {return idVenta;}

    public void setIdVenta(Long idVenta) {this.idVenta = idVenta;}

    public Long getIdPedido() {return idPedido;}

    public void setIdPedido(Long idPedido) {this.idPedido = idPedido;}

    public Long getIdProducto() {return idProducto;}

    public void setIdProducto(Long idProducto) {this.idProducto = idProducto;}

    public Long getCantidad() {return cantidad;}

    public void setCantidad(Long cantidad) {this.cantidad = cantidad;}

    public BigDecimal gettotal() {return total;}

    public void settotal(BigDecimal total) {this.total = total;}

}




























