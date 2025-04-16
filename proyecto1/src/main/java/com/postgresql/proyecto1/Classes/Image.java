package com.postgresql.proyecto1.Classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "image")

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_image;

    @NotNull
    private String owner;

    @NotNull
    private int taxon_shown;

    @NotNull
    private int license_id;

    @NotNull
    private int user_publisher;

    @NotNull
    private String place_image;
}
