package com.example.E_commerce.repositories;

import com.example.E_commerce.models.VentasModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentasRepository extends JpaRepository<VentasModel, Long> {
}
