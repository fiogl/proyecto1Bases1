package com.postgresql.proyecto1.model;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name="taxon_shown")
    private Taxon taxon;

    @NotNull
    @ManyToOne
    @JoinColumn(name="license_id")
    private License license;

    @NotNull
    @ManyToOne
    @JoinColumn(name="user_publisher")
    private User user;

    @NotNull
    private String place_image;

    @NotNull
    private String url;
}
