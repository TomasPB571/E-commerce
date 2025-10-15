package com.example.E_commerce.controllers;

import com.example.E_commerce.models.PedidosModel;
import com.example.E_commerce.servicies.PedidosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {

    private final PedidosService pedidosService;

    public PedidosController(PedidosService pedidosService) {this.pedidosService = pedidosService;}

    @GetMapping
    public List<PedidosModel> allPedidos() {return pedidosService.getAllPedidos();}

    @PostMapping
    public PedidosModel savePedidos(@RequestBody PedidosModel pedidosModel) {return pedidosService.savePedido(pedidosModel);}

    @GetMapping("/{id}")
    public ResponseEntity<PedidosModel> getPedidoById(@PathVariable Long id) {
        return pedidosService.getPedidoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidosModel> updatePedidoById(@PathVariable("id") Long idPedido, @RequestBody PedidosModel pedidoModel) {
        return pedidosService.getPedidoById(idPedido)
                .map(existing -> {
                    existing.setfechaPedido(pedidoModel.getfechaPedido());
                    existing.setEstado(pedidoModel.getEstado());
                    existing.setIdCliente(pedidoModel.getIdCliente());
                    return ResponseEntity.ok(pedidosService.savePedido(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedidoById(@PathVariable Long id) {

        if (pedidosService.getPedidoById(id).isPresent()) {

            pedidosService.deletePedidoById(id);
                return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchPedido(@PathVariable Long id, @RequestBody PedidosModel body) {
        return pedidosService.getPedidoById(id)
                .map(pedido -> {
                    boolean changed = false;

                    if (body.getEstado() != null) { pedido.setEstado(body.getEstado()); changed = true; }
                    if (body.getIdCliente() != null) { pedido.setIdCliente(body.getIdCliente()); changed = true; }

                    if (!changed) return ResponseEntity.badRequest().body("No enviaste campos para actualizar.");
                    return ResponseEntity.ok(pedidosService.savePedido(pedido));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
