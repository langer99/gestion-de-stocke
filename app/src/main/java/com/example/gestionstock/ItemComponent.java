package com.example.gestionstock;

import java.time.LocalDate;

public class ItemComponent {

    private int id_comp;
    private String name;
    private String date;

    private int quantity;
    private int categorie_id;


    public ItemComponent(int id_comp, String name, String date, int quantity, int categorie_id) {
        this.id_comp = id_comp;
        this.name = name;
        this.date = date;
        this.quantity = quantity;
        this.categorie_id = categorie_id;
    }

    public ItemComponent(String name, String date, int quantity, int categorie_id) {
        this.name = name;
        this.date = date;
        this.quantity = quantity;
        this.categorie_id = categorie_id;
    }

    public int getId_comp() {
        return id_comp;
    }

    public void setId_comp(int id_comp) {
        this.id_comp = id_comp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }
}
