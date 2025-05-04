package com.postgresql.proyecto1.service;

import com.postgresql.proyecto1.dto.AncestorDTO;
import com.postgresql.proyecto1.dto.ObservationTaxonomyDTO;
import com.postgresql.proyecto1.model.Observation;
import com.postgresql.proyecto1.model.Taxon;
import com.postgresql.proyecto1.repo.ObservationRepo;
import com.postgresql.proyecto1.repo.TaxonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaxonService {
    // Se importa los repositorios necesarios para las funciones
    @Autowired
    private TaxonRepo repo;
    @Autowired
    private ObservationRepo observationRepo;

    // Función para obtener la taxonomía de todas las observaciones disponibles
    public List<ObservationTaxonomyDTO> getTaxonomy() {
        // Se obtiene todas las observaciones
        List<Observation> observations = observationRepo.findAll();
        // Lista para ir asignando los valores
        List<ObservationTaxonomyDTO> result = new ArrayList<>();

        for (Observation observation : observations) {
            List<Object[]> taxonInfo = repo.findTaxonomy(observation.getTaxon().getId_taxon());
            List<AncestorDTO> taxonomia = new ArrayList<>();

            for (Object[] row : taxonInfo) {
                taxonomia.add(new AncestorDTO((Integer) row[0],
                        (String) row[1],
                        (String) row[2],
                        row[3] != null ? ((Number) row[3]).intValue() : null));
            }

            result.add(new ObservationTaxonomyDTO(observation.getId_observation(), taxonomia));
        }
        return result; // Se retorna la lista con los valores ya ordenados
    }

    // Llama a la función findKingdom del repositorio de taxón dado el id de un taxón
    public Taxon findKingdom(int taxonId) {
        return repo.findKingdom(taxonId);
    }

    // Llama a la función hasAncestors del repositorio de taxón dado el id de un taxón
    public boolean species(int taxonId) {
        return !repo.hasAncestors(taxonId);
    }
}

