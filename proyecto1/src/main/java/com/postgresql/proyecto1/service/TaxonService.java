package com.postgresql.proyecto1.service;

import com.postgresql.proyecto1.dto.AncestorDTO;
import com.postgresql.proyecto1.dto.IdentificationDTO;
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

    @Autowired
    private TaxonRepo repo;
    @Autowired
    private ObservationRepo observationRepo;

    //    // Función recursiva para encontrar el reino
//    public Taxon findKingdom(Taxon taxon) {
//        if (taxon.getAncestor() == null) {
//            return taxon;
//        }
//        return findKingdom(taxon.getAncestor());
//    }
//
    // Recorre los taxones actuales y encuentra el que se busca
    public Taxon findTaxonById_taxon(Integer taxonId) {
        return repo.findTaxonById(taxonId);
    }

    public List<ObservationTaxonomyDTO> getTaxonomy() {
        List<Observation> observations = observationRepo.findAll();
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

        return result;
    }

    public Taxon findKingdom(int taxonId) {
        return repo.findKingdom(taxonId);
    }

    public boolean species(int taxonId) {
        return !repo.hasAncestors(taxonId);
    }
}

