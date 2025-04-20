package com.postgresql.proyecto1.controller;

import com.postgresql.proyecto1.model.Identification;
import com.postgresql.proyecto1.repo.IdentificationRepo;
import com.postgresql.proyecto1.repo.ObservationRepo;
import com.postgresql.proyecto1.repo.TaxonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdentificationController {
    @Autowired
    IdentificationRepo identificationRepo;
    TaxonRepo taxonRepo;
    ObservationRepo observationRepo;

    @PostMapping("/addIdentification")
    public void addIdentification(@RequestBody Identification identification) {
        if (identification.getTaxon() == null || !taxonRepo.existsById(identification.getTaxon().getId_taxon())) {
            throw new IllegalArgumentException("The taxon doesn't exist.");
        }

        if (identification.getObservation() == null || !observationRepo.existsById(identification.getObservation().getId_observation())) {
            throw new IllegalArgumentException("The observation doesn't exist.");
        }

        identificationRepo.save(identification);
    }
}
