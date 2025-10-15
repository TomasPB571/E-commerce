package com.example.E_commerce.servicies;


import com.example.E_commerce.models.UsersModel;
import com.example.E_commerce.repositories.UsersInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServicie {

    private final UsersInterface usersInterface;

public UsersServicie(UsersInterface usersInterface){

    this.usersInterface= usersInterface;
}

    public UsersModel saveUser(UsersModel user){
        return usersInterface.save(user);
    }

    public List<UsersModel> getAllUsers(){return usersInterface.findAll(); }

    public Optional<UsersModel> getUsersById(Long idUser){ return usersInterface.findById(idUser); }

    public void deleteUsersById(Long idUser){usersInterface.deleteById(idUser); }


}
