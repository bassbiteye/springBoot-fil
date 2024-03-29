package com.fil.transfert.repository;

import com.fil.transfert.model.Compte;
import com.fil.transfert.model.Partenaire;
import com.fil.transfert.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
Optional<Compte> findCompteByNumCompte(String numCompte);
    @Query("SELECT c FROM Compte c WHERE c.partenaire = :partenaire")
    List<Compte> findallCompte(@Param("partenaire") Partenaire partenaire);

}
