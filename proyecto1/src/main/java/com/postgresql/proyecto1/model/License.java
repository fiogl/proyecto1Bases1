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
@Table(name = "license")

public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_license;

    @NotNull
    private String name;
}
