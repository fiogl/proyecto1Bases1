package com.postgresql.proyecto1.dto;

import lombok.Getter;

import java.util.List;

public class ObservationTaxonomyDTO {
    @Getter
    private Integer id;
    private List<AncestorDTO> taxonomia;

    public ObservationTaxonomyDTO(Integer id, List<AncestorDTO> taxonomia) {
        this.id = id;
        this.taxonomia = taxonomia;
    }

    public List<AncestorDTO> getTaxonomia() {
        return taxonomia;
    }
}
