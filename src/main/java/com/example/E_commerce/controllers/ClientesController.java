package com.example.E_commerce.controllers;

import com.example.E_commerce.models.Clientes;
import com.example.E_commerce.servicies.ClientesServicie;
import com.example.E_commerce.servicies.ClientesServicie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    private final ClientesServicie clientesServicie;

    public ClientesController(ClientesServicie clientesServicie) {
        this.clientesServicie = clientesServicie;
    }

    @GetMapping
    public List<Clientes> getAllClientes(){
        return clientesServicie.getAllClientes();
    }

    @PostMapping
    public Clientes saveClientes(@RequestBody Clientes clientes){
        return clientesServicie.saveCliente(clientes);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Clientes> getClientesById(@PathVariable("idCliente") Long idCliente){
        return clientesServicie.getClientesById(idCliente).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Clientes> updateClientesById(@PathVariable("idCliente") Long idCliente, @RequestBody Clientes clientes){
        return clientesServicie.getClientesById(idCliente).map(existing ->{
            existing.setNombre(clientes.getNombre());
            existing.setDireccion(clientes.getDireccion());
            existing.setTelefono(clientes.getTelefono());
            return ResponseEntity.ok(clientesServicie.saveCliente(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idCliente}")

    public ResponseEntity<Void> deleteClientes(@PathVariable Long idCliente) {
        if(clientesServicie.getClientesById(idCliente).isPresent()){
            clientesServicie.deleteClientesById(idCliente);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}

