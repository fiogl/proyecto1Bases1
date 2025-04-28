package com.postgresql.proyecto1.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "taxon")
public class Taxon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_taxon;

    @ManyToOne
    @JoinColumn(name = "id_ancestor")
    private Taxon ancestor;

    @NotNull
    private String scientific_name;

    @NotNull
    private String common_name;
}