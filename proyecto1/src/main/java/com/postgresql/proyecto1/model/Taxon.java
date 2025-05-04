package com.postgresql.proyecto1.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data // Genera automáticamente los métodos getter, setter, etc.
@Entity // Indica que esta clase es una entidad JPA (representa una tabla en la base de datos)
@Table(name = "taxon") // Nombre de la tabla

public class Taxon {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Valor autogenerado
    private int id_taxon;

    @ManyToOne // Relación muchos a uno con la entidad "Ancestor" (observación *..1 ancestro/taxon/).
    @JoinColumn(name = "id_ancestor")
    private Taxon ancestor;

    @NotNull
    private String scientific_name;

    @NotNull
    private String common_name;
}