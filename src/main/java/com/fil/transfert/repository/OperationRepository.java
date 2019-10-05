package com.fil.transfert.repository;

import com.fil.transfert.model.Compte;
import com.fil.transfert.model.Operation;
import com.fil.transfert.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("SELECT o FROM Operation o WHERE o.caissier = :user AND o.compte = :compte")
    Optional<Compte> findMesDepots(@Param("user") User caissier, @Param("compte") Compte compte);

  List<Operation> findOperationsByCaissier(User user);
}
