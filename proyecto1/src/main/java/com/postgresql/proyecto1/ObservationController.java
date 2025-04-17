package com.postgresql.proyecto1;

import com.postgresql.proyecto1.model.Observation;
import com.postgresql.proyecto1.repo.ObservationRepo;
import com.postgresql.proyecto1.repo.LicenseRepo;
import com.postgresql.proyecto1.repo.TaxonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ObservationController {
    @Autowired
    ObservationRepo observationRepo;
    TaxonRepo taxonRepo;

    @PostMapping("/addObservation")
    public void addObservation(@RequestBody Observation observation) {
        if (!taxonRepo.existsById(observation.getTaxon_id())) {
            throw new IllegalArgumentException("El taxon no existe.");
        }

        observationRepo.save(observation);
    }
}
