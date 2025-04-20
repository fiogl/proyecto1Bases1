package com.postgresql.proyecto1.controller;

import com.postgresql.proyecto1.model.Observation;
import com.postgresql.proyecto1.repo.ObservationRepo;
import com.postgresql.proyecto1.repo.TaxonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ObservationController {

    private final ObservationRepo observationRepo;
    private final TaxonRepo taxonRepo;

    @Autowired
    public ObservationController(ObservationRepo observationRepo, TaxonRepo taxonRepo) {
        this.observationRepo = observationRepo;
        this.taxonRepo = taxonRepo;
    }

    @PostMapping("/addObservation")
    public void addObservation(@RequestBody Observation observation) {
        if (observation.getTaxon() == null || !taxonRepo.existsById(observation.getTaxon().getId_taxon())) {
            throw new IllegalArgumentException("The taxon doesn't exist.");
        }

        observationRepo.save(observation);
    }
}
