package com.postgresql.proyecto1.controller;

import com.postgresql.proyecto1.dto.IdentificationDTO;
import com.postgresql.proyecto1.dto.ObservationDTO;
import com.postgresql.proyecto1.dto.ObservationTaxonomyDTO;
import com.postgresql.proyecto1.model.*;
import com.postgresql.proyecto1.repo.*;
import com.postgresql.proyecto1.service.IdentificationService;
import com.postgresql.proyecto1.service.TaxonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    // Inyección de dependencias de repositorios y servicios
    private final ObservationRepo observationRepo;
    private final TaxonRepo taxonRepo;
    private final LicenseRepo licenseRepo;
    private final UserRepo userRepo;
    private final TaxonService taxonService;
    private final ImageRepo imageRepo;
    private final IdentificationRepo identificationRepo;
    private final IdentificationService identificationService;

    // Metodo que maneja la solicitud GET a la página principal '/'
    @GetMapping("/")
    public String home(Model model) {
        // Se añaden todas las observaciones al modelo para ser mostradas en la vista 'home'
        model.addAttribute("observations", observationRepo.findAll());
        return "home";  // Retorna la vista 'home'
    }

    // Metodo que maneja la solicitud GET a la página '/general_consults'
    @GetMapping("/general_consults")
    public String getGeneralConsultsPage(Model model) {
        // Se obtienen todas las identificaciones a través del servicio y se añaden al modelo
        List<IdentificationDTO> identifications = identificationService.getAll();
        model.addAttribute("identifications", identifications);
        return "general_consults";  // Retorna la vista 'general_consults'
    }

    // Metodo que maneja la solicitud GET a la página '/taxonomy_consults'
    @GetMapping("/taxonomy_consults")
    public String getTaxonInfoConsultsPage(Model model) {
        // Se obtienen los datos de taxonomía a través del servicio y se añaden al modelo
        List<ObservationTaxonomyDTO> observationTaxonomy = taxonService.getTaxonomy();
        model.addAttribute("observationTaxonomy", observationTaxonomy);

        return "taxonomy_consults";  // Retorna la vista 'taxonomy_consults'
    }

    // Metodo que maneja la solicitud GET a la página '/taxons_consults'
    @GetMapping("/taxons_consults")
    public String getTaxonConsultPage(Model model) {
        // Se obtienen todas las observaciones
        List<Observation> observations = observationRepo.findAll();
        List<ObservationDTO> observationDTOs = new ArrayList<>();

        // Para cada observación, se obtiene su taxón, el reino y la especie
        for (Observation observation : observations) {
            Taxon taxon = observation.getTaxon();
            Taxon kingdom = taxonService.findKingdom(taxon.getId_taxon());
            boolean species = taxonService.species(taxon.getId_taxon());

            // Se crea un DTO con la información relevante de la observación y su taxón
            ObservationDTO observationDTO = new ObservationDTO(observation, kingdom, species);
            observationDTOs.add(observationDTO);
        }

        // Se añaden los DTOs al modelo para ser mostrados en la vista
        model.addAttribute("observations", observationDTOs);
        return "taxons_consults";  // Retorna la vista 'taxons_consults'
    }

    // Metodo que maneja la solicitud GET a la página de detalles de la observación
    @GetMapping("/observation_detail/{id}")
    public String getObservationDetails(@PathVariable Integer id, Model model) {
        // Se obtiene la observación por su ID con todos sus detalles
        Observation observation = observationRepo.findByIdWithDetails(id);
        // Se añaden la observación, los taxones y las licencias al modelo para ser mostrados
        model.addAttribute("observation", observation);
        model.addAttribute("taxon", taxonRepo.findAll());
        model.addAttribute("licenses", licenseRepo.findAll());
        return "observation_detail";  // Retorna la vista 'observation_detail'
    }

    // Metodo que maneja la actualización de una observación
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

        // Se obtiene la observación a actualizar
        Observation observation = observationRepo.findByIdWithDetails(id);

        // Se setea el usuario manualmente
        User user = userRepo.findByEmail("florita2901@gmail.com");
        observation.setUser(user);

        // Se actualizan los datos de la observación
        observation.setDate(LocalDate.parse(date));
        observation.setLatitude(latitude);
        observation.setLongitude(longitude);
        observation.setNotes(notes);

        // Se actualiza el taxón de la observación
        Taxon taxon = taxonRepo.findById(taxon_id).orElseThrow();
        observation.setTaxon(taxon);

        // Se actualiza la imagen asociada
        Image image = observation.getImage();
        image.setUrl(imageUrl);
        image.setOwner(imageOwner);
        image.setLatitude(imageLatitude);
        image.setLongitude(imageLongitude);

        // Se actualiza el taxón de la imagen
        Taxon imgTaxon = taxonRepo.findById(imageTaxon).orElseThrow();
        image.setTaxon(imgTaxon);

        // Se actualiza la licencia de la imagen
        License license = licenseRepo.findById(imageLicense).orElseThrow();
        image.setLicense(license);

        // Se guardan los cambios en la base de datos
        imageRepo.save(image);
        observationRepo.save(observation);

        // Redirige al detalle de la observación actualizada
        return "redirect:/observation_detail/" + id;
    }

    // Metodo que maneja la creación de una nueva identificación
    @PostMapping("/identifications/create")
    public String createIdentification(@RequestParam Integer observation_id,
                                       @RequestParam Integer taxon_id,
                                       @RequestParam String date) {

        // Se obtiene la observación y el taxón
        Observation observation = observationRepo.findByIdWithDetails(observation_id);
        Taxon taxon = taxonRepo.findById(taxon_id).orElseThrow();

        // Se setea el usuario manualmente
        User user = userRepo.findByEmail("florita2901@gmail.com");
        observation.setUser(user);

        // Se crea una nueva identificación
        Identification newIdentification = new Identification();
        newIdentification.setObservation(observation);
        newIdentification.setTaxon(taxon);
        newIdentification.setUser(user);
        newIdentification.setDate(LocalDate.parse(date));

        // Se guarda la identificación en la base de datos
        identificationRepo.save(newIdentification);

        // Redirige al detalle de la observación
        return "redirect:/observation_detail/" + observation_id;
    }

    // Metodo que maneja la actualización de una identificación existente
    @PostMapping("/identifications/update")
    public String updateIdentification(@RequestParam Integer id_identification,
                                       @RequestParam Integer taxon_id,
                                       @RequestParam String date,
                                       @RequestParam Integer observation_id) {

        // Se obtiene la identificación a actualizar
        Identification identification = identificationRepo.findById(id_identification).orElseThrow();
        Taxon taxon = taxonRepo.findById(taxon_id).orElseThrow();

        // Se actualizan los datos de la identificación
        identification.setTaxon(taxon);
        identification.setDate(LocalDate.parse(date));

        // Se guarda la identificación actualizada
        identificationRepo.save(identification);

        // Redirige al detalle de la observación
        return "redirect:/observation_detail/" + observation_id;
    }

    // Metodo que maneja la eliminación de una identificación
    @DeleteMapping("/delete_identification/{id}")
    @ResponseBody
    public String deleteIdentification(@PathVariable Integer id) {
        // Se elimina la identificación de la base de datos
        identificationRepo.deleteById(id);
        return "ok";  // Retorna un mensaje de éxito
    }

    // Metodo que maneja la solicitud GET a la página '/my_observations'
    @GetMapping("/my_observations")
    public String myObservations(Model model) {
        // Se obtienen todas las observaciones
        model.addAttribute("observations", observationRepo.findAll());
        return "my_observations";  // Retorna la vista 'my_observations'
    }

    // Metodo que muestra el formulario para crear una nueva observación
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Observation obs = new Observation();

        // Se añaden al modelo la observación y los taxones y licencias disponibles
        model.addAttribute("observation", obs);
        model.addAttribute("taxon", taxonRepo.findAll());
        model.addAttribute("license", licenseRepo.findAll());
        return "create_observation";  // Retorna la vista 'create_observation'
    }

    // Metodo que maneja la creación de una nueva observación
    @PostMapping("/create")
    public String createObservation(@ModelAttribute Observation observation) {
        // Se setea el usuario manualmente
        User user = userRepo.findByEmail("florita2901@gmail.com");
        observation.setUser(user);

        // Si la observación tiene una imagen, se setea el usuario para la imagen también
        if (observation.getImage() != null) {
            observation.getImage().setUser(user);
        }

        // Se guarda la nueva observación en la base de datos
        observationRepo.save(observation);

        // Redirige a la página principal
        return "redirect:/";
    }

    // Metodo que maneja la eliminación de una observación
    @DeleteMapping("/delete_observation/{id}")
    @ResponseBody
    public String delete_observation(@PathVariable Integer id) {
        // Se elimina la observación de la base de datos
        observationRepo.deleteById(id);
        return "ok"; // Retorna un mensaje de éxito
    }
}
