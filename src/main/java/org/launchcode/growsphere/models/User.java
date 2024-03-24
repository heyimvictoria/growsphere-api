package org.launchcode.growsphere.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {
    private String username;
    private String email;
    @ManyToMany
    private final List<Plant> plants = new ArrayList<>();

    // TODO: Getters, setters, toString

}
