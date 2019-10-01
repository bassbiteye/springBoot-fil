package com.fil.transfert.controller;


import com.fil.transfert.model.Partenaire;
import com.fil.transfert.model.Role;
import com.fil.transfert.model.User;
import com.fil.transfert.model.UserForm;
import com.fil.transfert.repository.PartRepository;
import com.fil.transfert.services.UserDetailsServiceImpl;
import com.fil.transfert.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class PartenaireController {
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    PartRepository partRepository;


    @PostMapping(value = "/addPar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER')")
        public Partenaire addPP(@RequestBody UserForm userForm) throws Exception {

     //   Partenaire Partenaire=new  Partenaire(userForm.getNom(),userForm.getPrenom(), userForm.getEtat(), userForm.getTelephone(), userForm.getImageName(),userForm.getUsername(),userForm.getEmail(),userForm.getPassword());


       // return partRepository.save(Partenaire);
        return null;
    }




}
