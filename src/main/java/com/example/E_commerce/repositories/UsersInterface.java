package com.example.E_commerce.repositories;

import com.example.E_commerce.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersInterface extends JpaRepository<UsersModel,Long> {
}
