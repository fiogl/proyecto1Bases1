package com.postgresql.proyecto1.controller;

import com.postgresql.proyecto1.model.Observation;
import com.postgresql.proyecto1.model.Taxon;
import com.postgresql.proyecto1.repo.ObservationRepo;
import com.postgresql.proyecto1.repo.TaxonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController

public class ObservationController {

    private final ObservationRepo observationRepo;
    private final TaxonRepo taxonRepo;

    @Autowired
    public ObservationController(ObservationRepo observationRepo, TaxonRepo taxonRepo) {
        this.observationRepo = observationRepo;
        this.taxonRepo = taxonRepo;
    }

    @GetMapping("/{id}/reino")
    public String getKingdom(Taxon taxon) {
        Taxon current = taxon;
        while (current != null) {
            // Suponiendo que el reino es el taxón sin antecesor
            if (current.getAncestor() == null) {
                return current.getScientific_name();
            }
            current = current.getAncestor();
        }
        return "Not found"; //No debería llegar a aquí en teoría
    }

    @GetMapping("/observacion/{id}")
    public String mostrarObservacion(@PathVariable int id, Model model) {
        Observation observation = observationRepo.findById(id).orElse(null);
        Taxon taxon = observation.getTaxon();
        String reino = getKingdom(taxon);

        model.addAttribute("obs", observation);
        model.addAttribute("reino", reino);

        return "observacion/vista";
    }

//    @PostMapping("/addObservation")
//    public void addObservation(@RequestBody Observation observation) {
//        if (observation.getTaxon() == null || !taxonRepo.existsById(observation.getTaxon().getId_taxon())) {
//            throw new IllegalArgumentException("The taxon doesn't exist.");
//        }
//
//        observationRepo.save(observation);
//    }
}
