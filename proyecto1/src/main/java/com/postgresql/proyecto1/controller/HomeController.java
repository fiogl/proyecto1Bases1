package com.postgresql.proyecto1.controller;

import com.postgresql.proyecto1.dto.IdentificationDTO;
import com.postgresql.proyecto1.dto.ObservationDTO;
import com.postgresql.proyecto1.model.*;
import com.postgresql.proyecto1.repo.*;
import com.postgresql.proyecto1.service.IdentificationService;
import com.postgresql.proyecto1.service.TaxonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.management.openmbean.CompositeData;
import java.time.LocalDate;
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
    private final ImageRepo imageRepo;
    private final IdentificationRepo identificationRepo;
    private final IdentificationService identificationService;

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
    public String getGeneralConsultsPage(Model model) {
        List<IdentificationDTO> identifications = identificationService.getAll();  // Usar la instancia del servicio
        model.addAttribute("identifications", identifications);
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
        model.addAttribute("taxon", taxonRepo.findAll());
        model.addAttribute("licenses", licenseRepo.findAll());
        return "observation_detail";
    }


    @PostMapping("/observations/update")
    public String updateObservation(@RequestParam int id,
                                    @RequestParam int taxon_id,
                                    @RequestParam String date,
                                    @RequestParam double latitude,
                                    @RequestParam double longitude,
                                    @RequestParam String notes,
                                    @RequestParam String imageUrl,
                                    @RequestParam String imageOwner,
                                    @RequestParam double imageLatitude,
                                    @RequestParam double imageLongitude,
                                    @RequestParam int imageTaxon,
                                    @RequestParam int imageLicense) {

        Observation observation = observationRepo.findByIdWithDetails(id);

        // Setea al user manual
        User user = userRepo.findByEmail("florita2901@gmail.com");
        observation.setUser(user);

        // Actualizar datos
        observation.setDate(LocalDate.parse(date));
        observation.setLatitude(latitude);
        observation.setLongitude(longitude);
        observation.setNotes(notes);

        // Actualizar taxón
        Taxon taxon = taxonRepo.findById(taxon_id).orElseThrow();
        observation.setTaxon(taxon);

        // Actualizar imagen
        Image image = observation.getImage();
        image.setUrl(imageUrl);
        image.setOwner(imageOwner);
        image.setLatitude(imageLatitude);
        image.setLongitude(imageLongitude);

        Taxon imgTaxon = taxonRepo.findById(imageTaxon).orElseThrow();
        image.setTaxon(imgTaxon);

        License license = licenseRepo.findById(imageLicense).orElseThrow();
        image.setLicense(license);

        imageRepo.save(image);
        observationRepo.save(observation);

        return "redirect:/observation_detail/" + id;
    }

    @PostMapping("/identifications/create")
    public String createIdentification(@RequestParam Integer observation_id,
                                       @RequestParam Integer taxon_id,
                                       @RequestParam String date) {

        Observation observation = observationRepo.findByIdWithDetails(observation_id);
        Taxon taxon = taxonRepo.findById(taxon_id).orElseThrow();

        User user = userRepo.findByEmail("florita2901@gmail.com");
        observation.setUser(user);

        Identification newIdentification = new Identification();
        newIdentification.setObservation(observation);
        newIdentification.setTaxon(taxon);
        newIdentification.setUser(user);
        newIdentification.setDate(LocalDate.parse(date));

        identificationRepo.save(newIdentification);

        return "redirect:/observation_detail/" + observation_id;  // Redirige al detalle de la observación
    }

    @DeleteMapping("/delete_identification/{id}")
    @ResponseBody
    public String deleteIdentification(@PathVariable Integer id) {
        identificationRepo.deleteById(id);
        return "ok";
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
        return "create_observation";
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
    public String delete_observation(@PathVariable Integer id) {
        observationRepo.deleteById(id);
        return "ok";
    }
}
