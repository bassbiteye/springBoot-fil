package com.fil.transfert.services;

import com.fil.transfert.model.Partenaire;
import com.fil.transfert.model.User;
import com.fil.transfert.repository.PartRepository;
import com.fil.transfert.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {
    @Autowired
    PartRepository partRepository;

    public Partenaire save(Partenaire partenaire){

        return partRepository.save(partenaire);
    }

    public List<Partenaire> findAll(){
        return partRepository.findAll();
    }
  /*  public Optional<Partenaire> findById(int id){
        return partRepository.findById(id);
    }*/

}
