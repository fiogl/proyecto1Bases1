package com.postgresql.proyecto1.dto;

import com.postgresql.proyecto1.model.Observation;
import com.postgresql.proyecto1.model.Taxon;

// Clase para los datos de la consulta de las observaciones
public class ObservationDTO {
    private Observation observation;
    private Taxon kingdom;
    private boolean isSpecies;

    public ObservationDTO(Observation observation, Taxon kingdom, boolean isSpecies) {
        this.observation = observation;
        this.kingdom = kingdom;
        this.isSpecies = isSpecies;
    }

    // Getters; A veces no detecta bien el lombok por lo que es necesario
    public Observation getObservation() {
        return observation;
    }

    public Taxon getKingdom() {
        return kingdom;
    }

    public boolean isSpecies() {
        return isSpecies;
    }
}
