package com.fil.transfert.model;

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

    @NotBlank
    @Size(min=3, max = 50)
    private String telephone;


    @NotBlank
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id", fetch = FetchType.LAZY)
    private User createdBy;

    @NotBlank
    @Size(min=3, max = 50)
    private String etat;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String description;

    @NotBlank
    @Size(min=6, max = 100)
    private String mail;
    @NotBlank
    @Size(min=6, max = 100)
    private String fix;

    public Partenaire() {}


}