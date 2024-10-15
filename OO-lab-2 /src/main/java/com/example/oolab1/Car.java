package com.example.oolab1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Utilisez GenerationType.IDENTITY ou GenerationType.AUTO selon votre besoin
    private Long id;  // ID unique auto-généré pour chaque voiture

    private String plateNumber;  // Plate number of the car
    private String model; // Model of the car
    private int price; // Price of the car
    private boolean rent; // Is rent?
    private String begin;
    private String ending;

    // Constructeur par défaut
    public Car() {
        this.plateNumber = "Unknown";
        this.model = "Unknown";
        this.price = 0;
        this.rent = false;
        this.begin = null;  // Remplacé par null pour indiquer l'absence de valeur
        this.ending = null;
    }

    // Constructeur avec arguments
    public Car(String plateNumber, String model, int price, boolean rent) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.price = price;
        this.rent = rent;
        this.begin = null;  // Peut être ajusté lors de la création de l'objet
        this.ending = null;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }
}
