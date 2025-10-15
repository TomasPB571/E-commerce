package com.example.E_commerce.servicies;


import com.example.E_commerce.models.Comerciantes;
import com.example.E_commerce.repositories.ComerciantesInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComerciantesServicie {

    private final ComerciantesInterface comerciantesInterface;

    public ComerciantesServicie(ComerciantesInterface comerciantesInterface) {
        this.comerciantesInterface = comerciantesInterface;
    }

    public Comerciantes saveComerciantes(Comerciantes comerciantes){
        return comerciantesInterface.save(comerciantes);
    }

    public List<Comerciantes> getAllComerciantes(){return comerciantesInterface.findAll();}

    public Optional<Comerciantes> getComerciantesById(Long idComerciante){return comerciantesInterface.findById(idComerciante);}

    public void deleteComerciantesById(Long idComerciante){comerciantesInterface.deleteById(idComerciante);}
}
