package com.fil.transfert.services;

import com.fil.transfert.model.User;
import com.fil.transfert.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {
    @Autowired
    UserRepository userRepository;

    public User save(User user){

        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(long id){
        return userRepository.findById(id);
    }
    public List<User> findUsersByPartenaireIsNullAndCompteIsNull(){
        return userRepository.findUsersByPartenaireIsNullAndCompteIsNull();}
}
