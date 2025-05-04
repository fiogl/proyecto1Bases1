package com.postgresql.proyecto1.dto;

import lombok.Getter;

// Clase para los datos del ancestro
public class AncestorDTO {
    private Integer idTaxon;
    private String commonName;
    @Getter
    private String scientificName;
    private Integer idAncestor;

    public AncestorDTO(Integer idTaxon, String commonName, String scientificName, Integer idAncestor) {
        this.idTaxon = idTaxon;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.idAncestor = idAncestor;
    }

    // Getters; A veces no detecta bien el lombok por lo que es necesario
    public String getCommonName() {
        return commonName;
    }

    public String getScientificName() {
        return scientificName;
    }
}
