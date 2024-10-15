package com.example.oolab1;

import java.util.Date;

public class Car {
    private String plateNumber;  // platenumber of the car
    private String model; // model of the car
    private int price; // Price of the car
    private boolean rent; // is rent ?
    private String begin;
    private String end;


    // constructeur par défaut
    public Car() {
        this.plateNumber = "Unknown";
        this.model = "Unknown";
        this.price = 0;
        this.rent = false;
        this.begin = "xx/xx/xxxx";
        this.end = "xx/xx/xxxx";
    }

    // Constructeur avec arguments
    public Car(String plateNumber, String model, int price, boolean rent) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.price = price;
        this.rent = rent;
    }


    //getters
    public String getPlateNumber() {
        return plateNumber;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public boolean isRent() {
        return rent;
    }

    // setters
    public void setRent(boolean rent) {
        this.rent = rent;
    }







    public String getBeggin_date() {
        return begin;
    }

    public void setBeggin_date(String beggin_date) {
        this.begin = beggin_date;
    }

    public String getEnd_date() {
        return end;
    }

    public void setEnd_date(String end_date) {
        this.end = end_date;
    }
}
