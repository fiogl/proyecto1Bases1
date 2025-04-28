package com.postgresql.proyecto1.controller;

import com.postgresql.proyecto1.model.Observation;
import com.postgresql.proyecto1.model.User;
import com.postgresql.proyecto1.repo.ObservationRepo;
import com.postgresql.proyecto1.repo.UserRepo;
import com.postgresql.proyecto1.repo.TaxonRepo;
import com.postgresql.proyecto1.repo.LicenseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ObservationRepo observationRepo;
    private final TaxonRepo taxonRepo;
    private final LicenseRepo licenseRepo;
    private final UserRepo userRepo;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("observations", observationRepo.findAll());
        return "home";
    }

    @GetMapping("/observations")
    public String listObservations(Model model) {
        List<Observation> observations = observationRepo.findAllWithTaxonAndUser();
        model.addAttribute("observations", observations);
        return "observations";
    }

    @GetMapping("/taxons_consults")
    public String getTaxonConsultPage(Model model) {
        List<Observation> observations = observationRepo.findAll();
        for (Observation obs : observations) {
            if (obs.getTaxon() != null && obs.getTaxon().getAncestor() != null) {
                obs.getTaxon().setScientific_name(
                        taxonRepo.findByAncestor(obs.getTaxon().getAncestor()).isEmpty()
                                ? obs.getTaxon().getScientific_name()
                                : "Not Found"
                );
            }
        }
        model.addAttribute("reino", 'x'); // prueba
        model.addAttribute("observations", observations);
        return "taxons_consults";
    }

    @GetMapping("/observation_detail/{id}")
    public String getObservationDetails(@PathVariable Integer id, Model model) {
        Observation observation = observationRepo.findByIdWithDetails(id);
        model.addAttribute("observation", observation);
        return "observation_detail";
    }

    @GetMapping("/my_observations")
    public String myObservations(Model model) {
        model.addAttribute("observations", observationRepo.findAll());
        return "my_observations";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Observation obs = new Observation();

        model.addAttribute("observation", obs);
        model.addAttribute("taxon", taxonRepo.findAll());
        model.addAttribute("license", licenseRepo.findAll());
        return "create";
    }

    @PostMapping("/create")
    public String createObservation(@ModelAttribute Observation observation) {
        User user = userRepo.findByEmail("florita2901@gmail.com");
        observation.setUser(user);

        if (observation.getImage() != null) {
            observation.getImage().setUser(user);
        }

        observationRepo.save(observation);
        return "redirect:/";
    }

    @DeleteMapping("/delete_observation/{id}")
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        observationRepo.deleteById(id);
        return "ok";
    }
}
