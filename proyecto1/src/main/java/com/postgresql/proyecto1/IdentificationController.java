package com.postgresql.proyecto1;

import com.postgresql.proyecto1.model.Identification;
import com.postgresql.proyecto1.repo.IdentificationRepo;
import com.postgresql.proyecto1.repo.ObservationRepo;
import com.postgresql.proyecto1.repo.TaxonRepo;
import com.postgresql.proyecto1.repo.UserRepo;
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
        if (!taxonRepo.existsById(identification.getTaxon_id())) {
            throw new IllegalArgumentException("El taxon no existe.");
        }

        if (!observationRepo.existsById(identification.getObservation_id())) {
            throw new IllegalArgumentException("La observación no existe.");
        }

        identificationRepo.save(identification);
    }
}
