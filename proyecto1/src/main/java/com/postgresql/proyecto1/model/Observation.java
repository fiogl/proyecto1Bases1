package com.postgresql.proyecto1.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "observation")

public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_observation;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "taxon_id")
    private Taxon taxon;

    @NotNull
    @OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
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
    @OneToMany(mappedBy = "observation",
               cascade = CascadeType.ALL, // Propaga operaciones
               orphanRemoval = true       // Elimina identificaciones "huérfanas"
    )
    private List<Identification> identifications = new ArrayList<>();
}
