package com.fil.transfert.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "partenaire", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "raisonSocial"
        }),
        @UniqueConstraint(columnNames = {
                "fix"
        })
})
public class Partenaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max = 50)
    private String raisonSocial;
    @NotBlank
    @Size(min=3, max = 50)
    private String adresse;
    @NotBlank
    @Size(min=3, max = 50)
    private String ninea;
    @JsonIgnore
    @OneToMany(mappedBy="partenaire")
    @JsonIgnoreProperties("partenaire")
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name="createdBy", nullable=true)
    @JsonIgnoreProperties("user")
    private User createdBy;

    public Set<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(Set<Compte> comptes) {
        this.comptes = comptes;
    }

    @OneToMany(mappedBy="partenaire")
    private Set<Compte> comptes;
    @NotBlank
    @Size(min=3, max = 50)
    private String etat;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    private String description;

    @NotBlank
    @Size(min=6, max = 100)
    private String mail;
    @NotBlank
    @Size(min=6, max = 100)
    private String fix;

    public Partenaire() {}

    public Partenaire(@NotBlank @Size(min = 3, max = 50) String raisonSocial, @NotBlank @Size(min = 3, max = 50) String adresse,@NotBlank @Size(min = 3, max = 50) String ninea, @NotBlank @Size(max = 50) @Email String description, @NotBlank @Size(min = 6, max = 100) String mail, @NotBlank @Size(min = 6, max = 100) String fix) {
        this.raisonSocial = raisonSocial;
        this.adresse = adresse;
        this.ninea = ninea;
        this.description = description;
        this.mail = mail;
        this.fix = fix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }
}