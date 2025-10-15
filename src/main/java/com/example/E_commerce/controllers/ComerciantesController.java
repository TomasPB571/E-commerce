package com.example.E_commerce.controllers;


import com.example.E_commerce.models.Comerciantes;
import com.example.E_commerce.repositories.ComerciantesInterface;
import com.example.E_commerce.servicies.ComerciantesServicie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comerciantes")
public class ComerciantesController {

    private final ComerciantesServicie comerciantesServicie;

    public ComerciantesController(ComerciantesServicie comerciantesServicie) {
        this.comerciantesServicie = comerciantesServicie;
    }

    @GetMapping
    public List<Comerciantes> getAllComerciantes(){
        return comerciantesServicie.getAllComerciantes();
    }

    @PostMapping
    public Comerciantes saveComerciantes(@RequestBody Comerciantes comerciantes){
        return comerciantesServicie.saveComerciantes(comerciantes);
    }

    @GetMapping("/{idComerciante}")
    public ResponseEntity<Comerciantes> getComerciantesById(@PathVariable Long idComerciante){
        return comerciantesServicie.getComerciantesById(idComerciante).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idComerciante}")
    public ResponseEntity<Comerciantes> updateComerciantesById(@PathVariable("idComerciante") Long idComerciante, @RequestBody Comerciantes comerciantes){
        return comerciantesServicie.getComerciantesById(idComerciante).map(existing ->{
            existing.setNombre(comerciantes.getNombre());
            existing.setNegocio(comerciantes.getNegocio());
            return ResponseEntity.ok(comerciantesServicie.saveComerciantes(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idComerciantes}")

    public ResponseEntity<Void> deleteComerciantesById(@PathVariable Long idComerciante){
        if(comerciantesServicie.getComerciantesById(idComerciante).isPresent()){
            comerciantesServicie.deleteComerciantesById(idComerciante);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
