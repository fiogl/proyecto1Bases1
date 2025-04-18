package com.postgresql.proyecto1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private int user_id;

    @NotNull
    private int taxon_id;

    @NotNull
    private LocalDate taxon_shown;

    @NotNull
    private String place_observation;

    private String notes;
}
