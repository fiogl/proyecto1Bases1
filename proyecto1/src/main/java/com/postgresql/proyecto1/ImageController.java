package com.postgresql.proyecto1;

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

    @PostMapping("/addImage")
    public void addImage(@RequestBody Image image) {
        if (!licenseRepo.existsById(image.getLicense_id())) {
            throw new IllegalArgumentException("La licencia no existe.");
        }

        if (!taxonRepo.existsById(image.getTaxon_shown())) {
            throw new IllegalArgumentException("El taxon no existe.");
        }



        imageRepo.save(image);
    }
}
