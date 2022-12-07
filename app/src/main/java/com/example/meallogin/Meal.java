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
    private boolean offered;
    private double rating;
    private int count;

    //region Constructors
    public Meal(){}
    public Meal(String name, String cuisineType, List<String> ingredients, List<String> allergens, double price, String description) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
        this.offered = false;
        this.rating = 0;
        this.count = 0;
    }

    //endregion

    //region Getters
    public String getName() {
        return this.name;
    }

    public String getCuisineType() {
        return cuisineType;
    }
    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getAllergens() {
        return this.allergens;
    }

    public double getPrice() {
        return this.price;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOffered() {return offered;}

    public double getRating() {return rating;}

    public int getCount() {return count;}

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

    public void setOffered(boolean offered) {this.offered = offered;}

    public void setRating(double rating) {
        this.count++;
        this.rating = (rating+this.rating)/this.count ;
    }

    public void setCount(int count) {this.count = count;}
    //endregion

    public boolean equals(Meal m) {
        if (this.name.equals(m.getName()) && this.cuisineType.equals(m.getCuisineType()) && this.ingredients.equals(m.getIngredients()) && this.allergens.equals(m.getAllergens()) && this.price == m.getPrice() && this.description.equals(m.getDescription())) {
            return true;
        }
        return false;
    }
    public String toString(){
        return this.name;
    }
}
