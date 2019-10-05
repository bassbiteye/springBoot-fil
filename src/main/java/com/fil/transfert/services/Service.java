package com.fil.transfert.services;

import com.fil.transfert.model.Compte;
import com.fil.transfert.model.Operation;
import com.fil.transfert.model.User;
import com.fil.transfert.repository.CompteRepository;
import com.fil.transfert.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    OperationRepository operationRepository;
    public Compte save(Compte compte){

        return compteRepository.save(compte);
    }
public  List<Operation> findOperationsByCaissier(User caissier){
        return operationRepository.findOperationsByCaissier(caissier);
}




}
