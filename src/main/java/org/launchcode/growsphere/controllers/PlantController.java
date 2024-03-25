package org.launchcode.growsphere.controllers;

import jakarta.validation.Valid;
import org.launchcode.growsphere.data.PlantRepository;
import org.launchcode.growsphere.models.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("plants")
public class PlantController {

    @Autowired
    private PlantRepository plantRepository;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Plants");
        model.addAttribute("plants", plantRepository.findAll());
        return "plants/index";
    }

    @GetMapping("add")
    public String displayAddPlantForm(Model model) {
        model.addAttribute(new Plant());
        return "plants/add";
    }

    @PostMapping("add")
    public String processAddPlantForm(@ModelAttribute @Valid Plant newPlant,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "plants/add";
        }
        plantRepository.save(newPlant);
        return "redirect:";
    }

    @GetMapping("view/{plantId}")
    public String displayViewPlant(Model model, @PathVariable int plantId) {

        Optional optPlant = plantRepository.findById(plantId);

        if (optPlant.isPresent()) {
            Plant plant = (Plant) optPlant.get();
            model.addAttribute("plant", plant);
            return "plants/view";
        } else {
            return "redirect:../";
        }
    }}
