package com.fil.transfert.repository;

import com.fil.transfert.model.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PartRepository extends JpaRepository<Partenaire, Long> {

}