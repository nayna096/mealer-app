package com.example.meallogin;

import java.io.Serializable;

public class Request implements Serializable {
    private Cook cook;
    private Client client;
    private Meal meal;
    private String description;
    private boolean status;
    private double rating;

    //region Constructors
    public Request (){}

    public Request(Meal meal, Cook cook, Client client, String description){
        this.meal = meal;
        this.cook = cook;
        this.client = client;
        this.description = description;
        this.status = false;
        this.rating = 0;
    }
    //end region

    //getters
    public Cook getCook() {
        return cook;
    }

    public Meal getMeal() {
        return meal;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    public double getRating() {return rating;}

    public Client getClient() {return client;}

    //end region

    //setters

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setRating(double rating) {this.rating = rating;}

    public void setClient(Client client) {this.client = client;}
    //end region

    public String toString(){return this.meal.getName();}
}
