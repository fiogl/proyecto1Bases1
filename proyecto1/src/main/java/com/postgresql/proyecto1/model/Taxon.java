package com.postgresql.proyecto1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "taxon")
public class Taxon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_taxon;

    @NotNull
    private int id_ancestor;

    @NotNull
    private String scientific_name;

    @NotNull
    private String common_name;
}