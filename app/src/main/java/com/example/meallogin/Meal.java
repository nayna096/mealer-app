package com.example.meallogin;

import java.io.Serializable;
public class Meal implements Serializable{

    private String name;
    private String cuisineType;
    private String[] ingredients;
    private String[] allergens;
    private double price;
    private String description;

    // Constructors
    public Meal(){}

    public Meal(String description){
        this.description = description;
    }

    public Meal(String name, String cuisineType, String[] ingredients, String[] allergens, double price, String description) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String[] getAllergens() {
        return allergens;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setName(String name) { // TODO Lisa - should visibility be private for all setters?
        this.name = name;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public void setAllergens(String[] allergens) {
        this.allergens = allergens;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
