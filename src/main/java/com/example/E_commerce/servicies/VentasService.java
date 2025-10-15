package com.example.E_commerce.servicies;

import com.example.E_commerce.models.VentasModel;
import com.example.E_commerce.repositories.VentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentasService {

    private final VentasRepository ventasRepository;

    public VentasService(VentasRepository ventasRepository) {this.ventasRepository = ventasRepository;}

    public VentasModel saveVenta(VentasModel ventasModel){
        return ventasRepository.save(ventasModel);
    }

    public List<VentasModel> getAllVentas(){
        return ventasRepository.findAll();
    }

    public Optional<VentasModel> getVentasById(Long id){
        return ventasRepository.findById(id);
    }

    public void deleteVentasById(Long id){
        ventasRepository.deleteById(id);
    }
}
