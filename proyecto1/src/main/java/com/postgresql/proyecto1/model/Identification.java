package com.postgresql.proyecto1.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "identification")

public class Identification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_identification;

    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="observation_id")
    private Observation observation;

    @NotNull
    @ManyToOne
    @JoinColumn(name="taxon_id")
    private Taxon taxon;

    @NotNull
    private LocalDate date;
}
