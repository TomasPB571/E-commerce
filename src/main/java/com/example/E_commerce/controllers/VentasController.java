package com.example.E_commerce.controllers;

import com.example.E_commerce.servicies.VentasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.example.E_commerce.models.VentasModel;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {

    private final VentasService ventasService;

    public VentasController(VentasService ventasService){
        this.ventasService = ventasService;
    }

    @GetMapping
    public List<VentasModel> allVentas() {return ventasService.getAllVentas();}

    @PostMapping
    public VentasModel saveVentas(@RequestBody VentasModel ventasModel) {return ventasService.saveVenta(ventasModel);}

    @GetMapping("/{id}")
    public ResponseEntity<VentasModel> getVentasById(@PathVariable Long id) {
        return ventasService.getVentasById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentasModel> updateVentasById(@PathVariable("id") Long idVenta, @RequestBody VentasModel ventasModel) {
        return ventasService.getVentasById(idVenta)
                .map(existing -> {
                    existing.setIdPedido(ventasModel.getIdPedido());
                    existing.setIdProducto(ventasModel.getIdProducto());
                    existing.setCantidad(ventasModel.getCantidad());
                    existing.settotal(ventasModel.gettotal());
                    return ResponseEntity.ok(ventasService.saveVenta(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVentasById(@PathVariable Long id) {
        if(ventasService.getVentasById(id).isPresent()) {
            ventasService.deleteVentasById(id);
                return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchVenta(@PathVariable Long id, @RequestBody VentasModel body) {
        return ventasService.getVentasById(id)
                .map(venta -> {
                    boolean changed = false;

                    if (body.getIdPedido() != null) { venta.setIdPedido(body.getIdPedido()); changed = true; }
                    if (body.getIdProducto() != null) { venta.setIdProducto(body.getIdProducto()); changed = true; }
                    if (body.getCantidad() != null) { venta.setCantidad(body.getCantidad()); changed = true; }
                    if (body.gettotal() != null) { venta.settotal(body.gettotal()); changed = true; }

                    if (!changed) return ResponseEntity.badRequest().body("No enviaste campos para actualizar.");
                    return ResponseEntity.ok(ventasService.saveVenta(venta));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}

















































