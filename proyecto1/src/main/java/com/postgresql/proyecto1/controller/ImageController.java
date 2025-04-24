package com.postgresql.proyecto1.controller;

import com.postgresql.proyecto1.model.Image;
import com.postgresql.proyecto1.repo.ImageRepo;
import com.postgresql.proyecto1.repo.LicenseRepo;
import com.postgresql.proyecto1.repo.TaxonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ImageController {
    @Autowired
    ImageRepo imageRepo;
    LicenseRepo licenseRepo;
    TaxonRepo taxonRepo;

//    @PostMapping("/addImage")
//    public void addImage(@RequestBody Image image) {
//        if (image.getLicense() == null || !taxonRepo.existsById(image.getLicense().getId_license())) {
//            throw new IllegalArgumentException("La licencia no existe.");
//        }
//
//        if (image.getTaxon() == null || !taxonRepo.existsById(image.getTaxon().getId_taxon())) {
//            throw new IllegalArgumentException("El taxon no existe.");
//        }
//
//
//
//        imageRepo.save(image);
//    }
}
