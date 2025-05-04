package com.postgresql.proyecto1.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data // Genera automáticamente los métodos getter, setter, etc.
@Entity // Indica que esta clase es una entidad JPA (representa una tabla en la base de datos)
@Table(name = "observation") // Nombre de la tabla

public class Observation {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Valor autogenerado
    private int id_observation;

    @NotNull
    @ManyToOne // Relación muchos a uno con la entidad "User" (observación *..1 usuario).
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne // Relación muchos a uno con la entidad "Taxon" (observación *..1 taxón).
    @JoinColumn(name = "taxon_id")
    private Taxon taxon;

    @NotNull
    // Relación uno a uno con la entidad "Image" (observación 1..1 image).
    @OneToOne (cascade = CascadeType.ALL, // Propaga operaciones
            orphanRemoval = true) // Elimina identificaciones "huérfanas"
    @JoinColumn(name = "image_id")
    private Image image;

    @NotNull
    private LocalDate date;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private String notes;

    // Si se elimina una observación, también se eliminan sus identificaciones
    @OneToMany(mappedBy = "observation",  // Relación uno a muchos (observación 1...* identificaciónes)
            cascade = CascadeType.ALL, // Propaga operaciones
            orphanRemoval = true       // Elimina identificaciones "huérfanas"
    )
    private List<Identification> identifications = new ArrayList<>();
}