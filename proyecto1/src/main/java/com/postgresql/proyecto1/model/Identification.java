package com.postgresql.proyecto1.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data // Genera automáticamente los métodos getter, setter, etc.
@Entity // Indica que esta clase es una entidad JPA (representa una tabla en la base de datos)
@Table(name = "identification") // Nombre de la tabla

public class Identification {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Valor autogenerado
    private int id_identification;

    @NotNull
    @ManyToOne // Relación muchos a uno con la entidad "User" (identificación *..1 usuario).
    @JoinColumn(name="user_id")
    private User user;

    @NotNull
    @ManyToOne // Relación muchos a uno con la entidad "Observation" (identificación *..1 observación).
    @JoinColumn(name="observation_id")
    private Observation observation;

    @NotNull
    @ManyToOne // Relación muchos a uno con la entidad "Taxon" (identificación *..1 taxón).
    @JoinColumn(name="taxon_id")
    private Taxon taxon;

    @NotNull
    private LocalDate date;
}