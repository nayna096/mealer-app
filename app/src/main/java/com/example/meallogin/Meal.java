package com.example.meallogin;

import java.io.Serializable;
import java.util.List;

public class Meal implements Serializable{

    private String name;
    private String cuisineType;
    private List<String> ingredients;
    private List<String> allergens;
    private double price;
    private String description;



    //region Constructors
    public Meal(){}

    public Meal(String description){
        this.description = description;
    }

    public Meal(String name, String cuisineType, List<String> ingredients, List<String> allergens, double price, String description) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
    }
    //endregion

    //region Getters
    public String getName() {
        return name;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
    //endregion

    //region Setters
    public void setName(String name) { // TODO Lisa - should visibility be private for all setters?
        this.name = name;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //endregion

    public boolean equals(Meal m) {
        if (this.name.equals(m.getName()) && this.cuisineType.equals(m.getCuisineType()) && this.ingredients.equals(m.getIngredients()) && this.allergens.equals(m.getAllergens()) && this.price == m.getPrice() && this.description.equals(m.getDescription())) {
            return true;
        }
        return false;
    }
}
