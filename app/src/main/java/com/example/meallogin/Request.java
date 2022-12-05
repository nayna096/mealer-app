package com.example.meallogin;

import java.io.Serializable;

public class Request implements Serializable {
    private Cook cook;
    private Meal meal;
    private String description;
    private boolean status;
    //region Constructors
    public Request (){}

    public Request(Meal meal, Cook cook, String description){
        this.meal = meal;
        this.cook = cook;
        this.description = description;
        this.status = false;
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
    //end region
}
