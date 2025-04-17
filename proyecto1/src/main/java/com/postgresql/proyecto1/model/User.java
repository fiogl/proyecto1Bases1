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
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
