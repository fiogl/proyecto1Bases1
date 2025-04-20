package com.postgresql.proyecto1.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

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
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    @NotNull
    private LocalDate date;

    @NotNull
    private String place_observation;

    private String notes;
}
