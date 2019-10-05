package com.fil.transfert.services;

import com.fil.transfert.model.Partenaire;
import com.fil.transfert.model.User;
import com.fil.transfert.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartService {
    @Autowired
    PartRepository partRepository;

    public Partenaire save(Partenaire partenaire){

        return partRepository.save(partenaire);
    }
    public Optional<Partenaire> findPartenaireByNinea(String ninea){
        return partRepository.findPartenaireByNinea(ninea);
    }

    public List<Partenaire> findAll(){
        return partRepository.findAll();
    }
    public List<Partenaire> findPartenaireByCreatedBy(User u){return  partRepository.findPartenaireByCreatedBy(u);}
    public Optional<Partenaire> findById(long id){
        return partRepository.findById(id);
    }
}
