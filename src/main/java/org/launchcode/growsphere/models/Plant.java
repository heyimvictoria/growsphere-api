package org.launchcode.growsphere.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Plant extends AbstractEntity {

    private String commonName;

    private String scientificName;

    @ManyToOne
    private PlantType plantType;

    // TODO: Water Requirements should use a date format?
    @ManyToOne
    private WaterReqs waterRequirements;

    // TODO: Sow could use a date format?
    private String sow;

    // TODO: Harvest could use a date format?
    private String harvest;

    @ManyToOne
    private SunExposure exposure;

    private double phLevel;

    // TODO: Fertilizer should use a date format?
    private String fertilizer;

    @ManyToMany(mappedBy = "plants")
    private final List<User> users = new ArrayList<>();

    // TODO: Getters, setters, toString

}
