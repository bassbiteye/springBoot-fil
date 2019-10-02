package com.fil.transfert.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max = 50)
    private String nom;
    @NotBlank
    @Size(min=3, max = 50)
    private String prenom;
    @NotBlank
    @Size(min=3, max = 50)
    private String etat;

    @NotBlank
    @Size(min=3, max = 50)
    private String telephone;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @NotBlank
    @Size(min=3, max = 50)
    private String imageName;

    @NotBlank
    @Size(min=3, max = 50)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min=6, max = 100)
    private String password;
    @ManyToOne
    @JoinColumn(name="partenaire_id", nullable=true)
    private Partenaire partenaire;

    @OneToMany(mappedBy="createdBy")
    private Set<Partenaire> partenaires;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(@NotBlank @Size(min = 3, max = 50) String nom, @NotBlank @Size(min = 3, max = 50) String prenom, @NotBlank @Size(min = 3, max = 50) String etat, @NotBlank @Size(min = 3, max = 50) String telephone, @NotBlank @Size(min = 3, max = 50) String imageName, @NotBlank @Size(min = 3, max = 50) String username, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 6, max = 100) String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.etat = etat;
        this.telephone = telephone;
        this.imageName = imageName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public Set<Partenaire> getPartenaires() {
        return partenaires;
    }

    public void setPartenaires(Set<Partenaire> partenaires) {
        this.partenaires = partenaires;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}