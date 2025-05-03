package com.postgresql.proyecto1.dto;

import lombok.Getter;

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

    public String getCommonName() {
        return commonName;
    }

    public String getScientificName() {
        return scientificName;
    }
}
