package com.example.E_commerce.controllers;

import com.example.E_commerce.repositories.UsersInterface;
import com.example.E_commerce.models.UsersModel;
import com.example.E_commerce.servicies.UsersServicie;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UsersServicie usersServicie;

    public UserController(UsersServicie usersServicie) {
        this.usersServicie = usersServicie;
    }

    @GetMapping
    public List<UsersModel> getAllUsers() {
        return usersServicie.getAllUsers();
    }

    @PostMapping
    public UsersModel saveUser(@RequestBody UsersModel user) {

        return usersServicie.saveUser(user);
    }


    @GetMapping("/{idUser}")
    public ResponseEntity<UsersModel> getUserById(@PathVariable Long idUser) {
    
        return usersServicie.getUsersById(idUser).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<UsersModel> updateUserById(@PathVariable Long idUser, @RequestBody UsersModel user) {
        return usersServicie.getUsersById(idUser).map(existing -> {
            existing.setFirstName(user.getFirstName());
            existing.setLastName(user.getLastName());
            return ResponseEntity.ok(usersServicie.saveUser(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idUser}")

    public ResponseEntity<Void> deleteUser(@PathVariable Long idUser) {
        if (usersServicie.getUsersById(idUser).isPresent()) {
            usersServicie.deleteUsersById(idUser);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{idUser}")
    public ResponseEntity<?> patchUser (@PathVariable Long idUser, @RequestBody UsersModel user) {

        return usersServicie.getUsersById(idUser).map(usersModel ->{
            boolean changed = false;

            if(user.getEstado()!= null){usersModel.setEstado(user.getEstado()); changed = true;}
            if(user.getIdUser() != null){usersModel.setIdUser(user.getIdUser()); changed = true;}

            if(!changed) return ResponseEntity.badRequest().body("No enviaste campos para actualizar.");
            return ResponseEntity.ok(usersServicie.saveUser(usersModel));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

