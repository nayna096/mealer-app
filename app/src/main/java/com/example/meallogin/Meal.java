package com.example.meallogin;

import java.io.Serializable;
public class Meal implements Serializable{
    private String description;

    public Meal(){}
    public Meal(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
