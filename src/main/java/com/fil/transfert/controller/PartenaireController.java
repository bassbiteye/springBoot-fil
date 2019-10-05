package com.fil.transfert.controller;


import com.fil.transfert.model.*;
import com.fil.transfert.repository.CompteRepository;
import com.fil.transfert.repository.OperationRepository;
import com.fil.transfert.repository.PartRepository;
import com.fil.transfert.services.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.random;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class PartenaireController {
    @Autowired
    PartRepository partRepository;
    @Autowired
    Service service;
    @Autowired
    OperationRepository operationRepository;
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
    @Autowired
    private PartService partService;
    @PostMapping(value = "/addP")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER')")
        public Message addPP(PartForm partForm) throws Exception {
            //ajout partenaire
        Partenaire partenaire= new  Partenaire(partForm.getRaisonSocial(),partForm.getAdresse(),partForm.getNinea(),partForm.getDescription(),partForm.getMail(),partForm.getFix());
           User uConnect = userDetailsService.getUserconnected();
            partenaire.setCreatedBy(uConnect);
            partenaire.setEtat("acitf");
            partRepository.save(partenaire);
            //ajout compte
            Compte compte = new Compte();
            double x = (int)(Math.random()*((999999)+10));
            compte.setSolde((long) 0);
            compte.setNumCompte(String.valueOf(x));
            compte.setPartenaire(partenaire);
            compteService.save(compte);
            //ajout admin partenaire
            User user=new  User(partForm.getNom(),partForm.getPrenom(), partForm.getEtat(), partForm.getTelephone(), partForm.getImageName(),partForm.getUsername(),partForm.getEmail(),partForm.getPassword());
            user.setPassword (encoder.encode(user.getPassword()));
            user.setEtat("acitf");
            Set<Role> roles = new HashSet<>();
            Role role=new Role();
            role.setId((long) 1);
            roles.add(role);
            user.setRoles(roles);
            user.setCompte(compte);
            user.setPartenaire(partenaire);
             userService.save(user);

            Message message = new Message(200,"partenaire ajouté avec success");
            return message;
    }
    @PreAuthorize("hasAuthority('ROLE_SUPER')")
    @GetMapping(value = "/compte", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Compte> compte(){
        return compteRepository.findAll();
    }
    @PostMapping(value = "/depot")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER')")
    public Message addc(@RequestBody PartForm partForm) throws Exception {
               Compte compte = compteRepository.findCompteByNumCompte(partForm.getNumCompte()).orElseThrow(
                        () -> new Exception("Compte introuvable")
                );
                if(compte!= null){
                    User uConnect = userDetailsService.getUserconnected();
                    Operation operation = new Operation();
                    operation.setCaissier(uConnect);
                    operation.setDateDepot(new Date());
                    operation.setCompte(compte);
                    operation.setMontantdepose(partForm.getSolde());
                    operation.setMonatantAvantDepot(compte.getSolde());
                    compte.setSolde(compte.getSolde()+operation.getMontantdepose());
                    operationRepository.save(operation);
                    compteService.save(compte);
                    Message message = new Message(200,"  depot fait avec sucess");
                    return message;
                }
        Message message = new Message(200,"erreur");
        return message;
    }
    @PostMapping(value = "/addCompte")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER')")
    public Message depot(@RequestBody PartForm partForm) throws Exception {
        Partenaire part = partService.findPartenaireByNinea(partForm.getNinea()).orElseThrow(
                () -> new Exception("Partenaire introuvable")
        );
        if(part!= null){
            Compte compte = new Compte();
            int x = (int)(Math.random()*((999999)+10));
            compte.setSolde((long) 0);
            compte.setNumCompte(String.valueOf(x));
            compte.setPartenaire(part);
            compteService.save(compte);
            Message message = new Message(200,"Le nouveau compte est  "+compte.getNumCompte()+"");
            return message;
        }
        Message message = new Message(200,"erreur");
        return message;
    }
    @PreAuthorize("hasAuthority('ROLE_SUPER') or hasAuthority('ROLE_ADMIN')")
    @PutMapping(value = "bloquer/{id}")
    public Message bloquer(@PathVariable("id") long id) throws Exception{
        Partenaire part = partService.findById(id).orElseThrow(
                () -> new Exception("partenaire introuvable")
        );
        if (part != null) {
            if(part.getEtat().equals("bloquer")){
                part.setEtat("actif");
                partService.save(part);
                Message message = new Message(200,"le partenaire est actif");
                return message;
            }
            else if(part.getEtat().equals("actif")){
                part.setEtat("bloquer");
                partService.save(part);
                Message message = new Message(200,"le partenaire est bloqué");
                return message;
            }
        }
        Message message = new Message(200,"partenaire introuvable");
        return message;
    }

    @PostMapping(value = "/findCompte")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER')")
    public Compte findCompte(PartForm partForm) throws Exception {
        Compte compte = compteRepository.findCompteByNumCompte(partForm.getNumCompte()).orElseThrow(
                () -> new Exception("Compte introuvable")
        );

        return compte;
    }
    @GetMapping(value = "/findallCompte")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER')")
    public List <Compte> findallCompte() throws Exception {
        User uConnect = userDetailsService.getUserconnected();

        List compte = compteRepository.findallCompte(uConnect.getPartenaire());

        return compte;
    }
    @GetMapping(value = "/liste")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER')")
    public List <Partenaire> listePar() throws Exception {
        User uConnect = userDetailsService.getUserconnected();

        List partenaire = partRepository.findPartenaireByCreatedBy(uConnect);

        return partenaire;
    }

    @PostMapping(value = "/findPar")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER')")
    public Partenaire findPar(PartForm partForm) throws Exception {
        Partenaire part = partService.findPartenaireByNinea(partForm.getNinea()).orElseThrow(
                () -> new Exception("Partenaire introuvable")
        );

     return part;
    }
    @GetMapping(value = "/history")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER')")
    public List<Operation> finhistorydPar() throws Exception {
        User userconnected = userDetailsService.getUserconnected();
        List operation = service.findOperationsByCaissier(userconnected);
        return operation;
    }
}
