package com.example.E_commerce.repositories;

import com.example.E_commerce.models.Comerciantes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComerciantesInterface extends JpaRepository<Comerciantes,Long> {
}
