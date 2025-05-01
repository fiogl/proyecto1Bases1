package com.postgresql.proyecto1.controller;

import com.postgresql.proyecto1.dto.ObservationDTO;
import com.postgresql.proyecto1.model.Observation;
import com.postgresql.proyecto1.model.Taxon;
import com.postgresql.proyecto1.model.User;
import com.postgresql.proyecto1.repo.ObservationRepo;
import com.postgresql.proyecto1.repo.UserRepo;
import com.postgresql.proyecto1.repo.TaxonRepo;
import com.postgresql.proyecto1.repo.LicenseRepo;
import com.postgresql.proyecto1.service.TaxonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ObservationRepo observationRepo;
    private final TaxonRepo taxonRepo;
    private final LicenseRepo licenseRepo;
    private final UserRepo userRepo;
    private final TaxonService taxonService;

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

    @GetMapping("/general_consults")
    public String getGeneralConsultPage(Model model) {
        model.addAttribute("observations", observationRepo.findAll());
        return "general_consults";
    }

    @GetMapping("/taxons_consults")
    public String getTaxonConsultPage(Model model) {
        List<Observation> observations = observationRepo.findAll();
        List<ObservationDTO> observationDTOs = new ArrayList<>();

        for (Observation observation : observations) {
            Taxon taxon = observation.getTaxon();
            Taxon kingdom = taxonService.findKingdom(taxon.getId_taxon());
            boolean species = taxonService.species(taxon.getId_taxon());

            ObservationDTO observationDTO = new ObservationDTO(observation, kingdom, species);
            observationDTOs.add(observationDTO);
        }

        model.addAttribute("observations", observationDTOs);
        return "taxons_consults";
    }

//    @GetMapping("/taxons_consults/{id}")
//    public String getTaxon(@PathVariable Integer id, Model model) {
//        Taxon kingdom = taxonService.findKingdom(id);
//        boolean isSpecies = taxonService.species(id);
//
//        model.addAttribute("kingdom", kingdom);
//
//        if (isSpecies) {
//            Taxon taxon = taxonService.findTaxonById_taxon(id);
//            model.addAttribute("species", taxon.getScientific_name());
//        } else {
//            model.addAttribute("species", "No disponible");
//        }
//        return "taxons_consults";
//    }

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
