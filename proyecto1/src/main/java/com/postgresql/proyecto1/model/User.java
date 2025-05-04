package com.postgresql.proyecto1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data // Genera automáticamente los métodos getter, setter, etc.
@Entity // Indica que esta clase es una entidad JPA (representa una tabla en la base de datos)
@Table(name = "users") // Nombre de la tabla

public class User {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Valor autogenerado
    private int id_user;

    @NotNull
    private String name;

    @NotNull
    private String last_name;

    @NotNull
    private String country;

    @NotNull
    private String address;

    @NotNull
    private String email;
}