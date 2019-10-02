package com.fil.transfert.controller;


import com.fil.transfert.model.*;
import com.fil.transfert.repository.CompteRepository;
import com.fil.transfert.repository.PartRepository;
import com.fil.transfert.services.CompteService;
import com.fil.transfert.services.UserDetailsServiceImpl;
import com.fil.transfert.services.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class PartenaireController {
    @Autowired
    PartRepository partRepository;
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    CompteService compteService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private UserService userService;
    @PostMapping(value = "/addPar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER')")
        public void addPP(@RequestBody PartForm partForm) throws Exception {
            //ajout partenaire
        Partenaire partenaire= new  Partenaire(partForm.getRaisonSocial(),partForm.getAdresse(),partForm.getNinea(),partForm.getDescription(),partForm.getMail(),partForm.getFix());
           User uConnect = userDetailsService.getUserconnected();
            partenaire.setCreatedBy(uConnect);
            partenaire.setEtat("acitf");
            partRepository.save(partenaire);

            //ajout admin partenaire
            User user=new  User(partForm.getNom(),partForm.getPrenom(), partForm.getEtat(), partForm.getTelephone(), partForm.getImageName(),partForm.getUsername(),partForm.getEmail(),partForm.getPassword());
            user.setPassword (encoder.encode(user.getPassword()));
            Set<Role> roles = new HashSet<>();
            Role role=new Role();
            role.setId((long) 1);
            roles.add(role);
            user.setRoles(roles);
            user.setPartenaire(partenaire);
             userService.save(user);
            //ajout compte
            Compte compte = new Compte();
            compte.setSolde("355500");
            compte.setNumCompte("10000");
            compte.setPartenaire(partenaire);
            compteService.save(compte);


    }


}
