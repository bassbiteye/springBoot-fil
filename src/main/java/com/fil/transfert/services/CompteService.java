package com.fil.transfert.services;

import com.fil.transfert.model.Compte;
import com.fil.transfert.model.Partenaire;
import com.fil.transfert.model.User;
import com.fil.transfert.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {
    @Autowired
    CompteRepository compteRepository;

    public Compte save(Compte compte){

        return compteRepository.save(compte);
    }

    public List<Compte> findAll(){
        return compteRepository.findAll();
    }
public Optional<Compte> findCompteByNumCompte(String numCompte){
        return compteRepository.findCompteByNumCompte(numCompte);
}
public List<Compte> findallCompte(Partenaire p){
        return compteRepository.findallCompte(p);
}



}
