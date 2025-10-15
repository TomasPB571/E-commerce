package com.example.E_commerce.servicies;

import com.example.E_commerce.models.Clientes;
import com.example.E_commerce.repositories.ClientesInterfaces;
import com.example.E_commerce.models.Clientes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesServicie {

    private final ClientesInterfaces clientesInterface;

    public ClientesServicie(ClientesInterfaces clientesInterface){
        this.clientesInterface = clientesInterface;
    }

    public Clientes saveCliente(Clientes cliente){
        return clientesInterface.save(cliente);
    }

    public List<Clientes> getAllClientes(){ return clientesInterface.findAll(); }

    public Optional<Clientes> getClientesById(Long idCliente){return clientesInterface.findById(idCliente);}

    public void deleteClientesById(Long idCliente){clientesInterface.deleteById(idCliente);}


}
