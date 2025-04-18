package com.postgresql.proyecto1;

import com.postgresql.proyecto1.model.License;
import com.postgresql.proyecto1.repo.LicenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LicenseController {
    @Autowired
    LicenseRepo licenseRepo;

    @PostMapping("/addLicense")
    public void addImage(@RequestBody License license) {
        licenseRepo.save(license);
    }
}
