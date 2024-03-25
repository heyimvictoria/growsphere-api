package org.launchcode.growsphere.services;

import ch.qos.logback.core.model.Model;
import org.launchcode.growsphere.data.PlantRepository;
import org.launchcode.growsphere.data.UserRepository;
import org.launchcode.growsphere.models.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private UserRepository userRepository;

    public PlantService() {
    }

    @PostMapping("/plants")
    public Plant createPlant(Model Plant plant) {
        return plantRepository.save(plant);

    }
    @GetMapping("/plants")
    public List<Plant> getAllPlants() {
        return (List<Plant>) plantRepository.findAll();
    }
    @GetMapping("/plants/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable Long id) {
        Plant plant = plantRepository.findById(id);
        return ResponseEntity.ok().body(plant);
    }

    @PutMapping("/plant/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable Long id, @RequestBody Plant plantDetails) {
        Plant plant = plantRepository.findById(id);
        plant.setName(plantDetails.getName());

        //example fields/getters/setters - will update //
        plant.setScientificName(plantDetails.getScientificName());

        plant.setWateringFrequency(plantDetails.getWateringFrequency());
        final Plant updatedPlant = plantRepository.save(plant);

        return ResponseEntity.ok(updatedPlant);
    }

    @DeleteMapping("/plant/{id}")
    public ResponseEntity<Plant> deletePlant(@PathVariable Long id) {
        plantRepository.delete(id);
        return ResponseEntity.ok().build();
    }

}
