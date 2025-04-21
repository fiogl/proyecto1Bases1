package com.postgresql.proyecto1.controller;

import com.postgresql.proyecto1.model.Image;
import com.postgresql.proyecto1.model.Observation;
import com.postgresql.proyecto1.repo.ObservationRepo;
import com.postgresql.proyecto1.repo.TaxonRepo;
import com.postgresql.proyecto1.repo.LicenseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ObservationRepo observationRepo;
    private final TaxonRepo taxonRepo;
    private final LicenseRepo licenseRepo;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("observations", observationRepo.findAll());
        return "home";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Observation obs = new Observation();
        obs.setImage(new Image());
        model.addAttribute("observation", obs);


        model.addAttribute("taxon", taxonRepo.findAll());
        model.addAttribute("license", licenseRepo.findAll());
        return "create";
    }

    @PostMapping("/create")
    public String createObservation(@ModelAttribute Observation observation) {
        observationRepo.save(observation);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        observationRepo.deleteById(id);
        return "ok";
    }
}
