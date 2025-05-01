package com.postgresql.proyecto1.controller;

import com.postgresql.proyecto1.model.Taxon;
import com.postgresql.proyecto1.repo.TaxonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class TaxonController {
    @Autowired
    TaxonRepo repo;

//    @PostMapping("/addTaxon")
//    //Verifica que el antecesor exista o que sea cero para crearlo
//    public void addTaxon(@RequestBody Taxon taxon) {
//        if (taxon.getId_ancestor() != 0 && !repo.existsById(taxon.getId_ancestor())) {
//            throw new IllegalArgumentException("El id_ancestor no existe en la tabla taxon.");
//        }
//
//        repo.save(taxon);
//    }
}
