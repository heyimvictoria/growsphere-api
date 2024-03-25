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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    @Override
    public String toString() {
        return username;
    }
}
