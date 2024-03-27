package org.launchcode.growsphere.controllers;

import org.launchcode.growsphere.data.PlantRepository;
import org.launchcode.growsphere.exceptions.PlantNotFoundException;
import org.launchcode.growsphere.models.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class PlantController {

    @Autowired
    private PlantRepository plantRepository;

    @PostMapping("/plant")
    Plant newPlant(@RequestBody Plant newPlant) {
        return plantRepository.save(newPlant);
    }

    @GetMapping("/plants")
    List<Plant> getAllPlants() {
       return (List<Plant>) plantRepository.findAll();
    }

    @GetMapping("/plant/{id}")
    Plant getPlantById(@PathVariable int id) {
        return plantRepository.findById(id)
                .orElseThrow(() ->new PlantNotFoundException(id));
    }
    @PutMapping("/plant/{id}")
    Plant updatePlant(@RequestBody Plant newPlant, @PathVariable int id) {
        return plantRepository.findById(id)
                .map(plant -> {
                    plant.setCommonName(newPlant.getCommonName());
                    plant.setScientificName(newPlant.getScientificName());
                    plant.setPlantType(newPlant.getPlantType());
                    plant.setExposure(newPlant.getExposure());
                    plant.setFertilizer(newPlant.getFertilizer());
                    plant.setHarvest(newPlant.getHarvest());
                    plant.setPhLevel(newPlant.getPhLevel());
                    plant.setSow(newPlant.getSow());
                    plant.setWaterRequirements(newPlant.getWaterRequirements());

                    return plantRepository.save(plant);
                }).orElseThrow(()->new PlantNotFoundException(id));

    }
    @DeleteMapping("/plant/{id}")
    String deletePlant(@PathVariable int id) {
        if(!plantRepository.existsById(id)) {
            throw new PlantNotFoundException(id);
        }
        plantRepository.deleteById(id);
        return "Plant with ID:" + id + " has been successfully deleted.";
    }
}

