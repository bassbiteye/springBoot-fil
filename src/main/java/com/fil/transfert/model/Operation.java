package com.fil.transfert.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;



@Entity
@Table(name = "operation")

public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long montantdepose;

    private Long monatantAvantDepot;
    @DateTimeFormat(pattern = "yyyy-MM-dd-mm-ss")
    private Date DateDepot;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="compte", nullable=false)
    @JsonIgnoreProperties("compte")
    private Compte compte;
    @ManyToOne
    @JoinColumn(name="caissier", nullable=false)
    @JsonIgnoreProperties("caissier")
    private User caissier;

    public Operation() {
    }

    public Operation(@NotBlank Long montantdepose, @NotBlank Long monatantAvantDepot, @NotBlank Date dateDepot, Compte compte, User caissier) {
        this.montantdepose = montantdepose;
        this.monatantAvantDepot = monatantAvantDepot;
        DateDepot = dateDepot;
        this.compte = compte;
        this.caissier = caissier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMontantdepose() {
        return montantdepose;
    }

    public void setMontantdepose(Long montantdepose) {
        this.montantdepose = montantdepose;
    }

    public Long getMonatantAvantDepot() {
        return monatantAvantDepot;
    }

    public void setMonatantAvantDepot(Long monatantAvantDepot) {
        this.monatantAvantDepot = monatantAvantDepot;
    }

    public Date getDateDepot() {
        return DateDepot;
    }

    public void setDateDepot(Date dateDepot) {
        DateDepot = dateDepot;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public User getCaissier() {
        return caissier;
    }

    public void setCaissier(User caissier) {
        this.caissier = caissier;
    }
}