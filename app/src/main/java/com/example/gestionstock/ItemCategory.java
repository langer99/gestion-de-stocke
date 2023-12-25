package com.example.gestionstock;

import androidx.annotation.NonNull;

public class ItemCategory {
    private int id_cat;
    private String name, description;


    public ItemCategory(int id_cat, String name, String description) {
        this.id_cat = id_cat;
        this.name = name;
        this.description = description;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
    
}
