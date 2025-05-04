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
}
