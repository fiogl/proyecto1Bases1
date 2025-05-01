package com.postgresql.proyecto1.service;

import com.postgresql.proyecto1.model.Taxon;
import com.postgresql.proyecto1.repo.TaxonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxonService {

    @Autowired
    private TaxonRepo repo;

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

    public Taxon findKingdom(int taxonId) {
        return repo.findKingdom(taxonId);
    }

    public boolean species(int taxonId) {
        return !repo.hasAncestors(taxonId);
    }
}

