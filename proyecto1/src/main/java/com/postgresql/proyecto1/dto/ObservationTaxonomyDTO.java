package com.postgresql.proyecto1.dto;

import lombok.Getter;
import java.util.List;

// Clase para los datos de la consulta de las taxonomías de las observaciones
public class ObservationTaxonomyDTO {
    @Getter
    private Integer id;
    private List<AncestorDTO> taxonomia;

    public ObservationTaxonomyDTO(Integer id, List<AncestorDTO> taxonomia) {
        this.id = id;
        this.taxonomia = taxonomia;
    }

    // Getter; A veces no detecta bien el lombok por lo que es necesario
    public List<AncestorDTO> getTaxonomia() {
        return taxonomia;
    }
}
