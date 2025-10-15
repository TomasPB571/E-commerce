package com.example.E_commerce.servicies;

import com.example.E_commerce.models.PedidosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.E_commerce.repositories.PedidosRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class PedidosService {

    private final PedidosRepository pedidosRepository;

    public PedidosService(PedidosRepository pedidosRepository) {this.pedidosRepository = pedidosRepository;}

    public PedidosModel savePedido(PedidosModel pedidosModel){
        if(pedidosModel.getfechaPedido() == null){
            pedidosModel.setfechaPedido(LocalDateTime.now(ZoneId.of("America/Bogota")));
        }
        return pedidosRepository.save(pedidosModel);
    }
    public List<PedidosModel> getAllPedidos() {return pedidosRepository.findAll();}

    public Optional<PedidosModel> getPedidoById(Long id) {return pedidosRepository.findById(id);}

    public void deletePedidoById(Long id) {pedidosRepository.deleteById(id);}
}

