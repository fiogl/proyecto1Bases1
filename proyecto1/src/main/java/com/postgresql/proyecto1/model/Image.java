package com.postgresql.proyecto1.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data // Genera automáticamente los métodos getter, setter, etc.
@Entity // Indica que esta clase es una entidad JPA (representa una tabla en la base de datos)
@Table(name = "image") // Nombre de la tabla

public class Image {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Valor autogenerado
    private int id_image;

    @NotNull
    private String owner;

    @NotNull
    @ManyToOne // Relación muchos a uno con la entidad "Taxon" (imagen *..1 taxón).
    @JoinColumn(name="taxon_shown")
    private Taxon taxon;

    @NotNull
    @ManyToOne // Relación muchos a uno con la entidad "Taxon" (imagen *..1 licencia).
    @JoinColumn(name="license_id")
    private License license;

    @NotNull
    @ManyToOne // Relación muchos a uno con la entidad "Taxon" (imagen *..1 usuario).
    @JoinColumn(name="user_publisher")
    private User user;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    @NotNull
    private String url;
}