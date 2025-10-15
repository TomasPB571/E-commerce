package com.example.E_commerce.repositories;

import com.example.E_commerce.models.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesInterfaces extends JpaRepository<Clientes,Long> {
}
