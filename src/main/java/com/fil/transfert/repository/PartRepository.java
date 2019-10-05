package com.fil.transfert.repository;

import com.fil.transfert.model.Partenaire;
import com.fil.transfert.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PartRepository extends JpaRepository<Partenaire, Long> {
Optional<Partenaire> findPartenaireByNinea(String ninea);
List<Partenaire> findPartenaireByCreatedBy(User u);
}