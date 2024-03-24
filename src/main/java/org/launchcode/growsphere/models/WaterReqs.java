package org.launchcode.growsphere.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class WaterReqs extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "waterRequirements")
    private final List<Plant> plants = new ArrayList<>();

    // TODO: Getters, setters, toString

}
