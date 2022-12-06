package com.example.meallogin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable {
    private ArrayList<Meal> meals;
//    private ArrayList<Meal> offered;
    private String placeholder = "Empty"; //needed to keep Menu from becoming null
    //region Constructors


    public Menu() {
        this.meals = new ArrayList<Meal>();
//        this.offered = new ArrayList<Meal>();
    }
    //endregion




    //region Getters
    public ArrayList<Meal> getMeals() {
        return meals;
    }

//    public ArrayList<Meal> getOffered() {
//        return offered;
//    }

    public String getPlaceholder() {return placeholder;}

    //endregion

    //region Setters
    public void setMeals(ArrayList<Meal> menu) {
        this.meals = menu;
    }

//    public void setOffered(ArrayList<Meal> offered) {
//        this.offered = offered;
//    }

    public void setPlaceholder(String placeholder) {this.placeholder = placeholder;}
    //endregion

    //region non-suspended cook only
//    public void addtoMeals(Meal meal) {
//        if (!(this.meals.contains(meal))) {
//            this.meals.add(meal);
//        }
//    }

    public void deletefromMeals(Meal meal) {
        if (this.meals.contains(meal)) {
            this.meals.remove(meal);
//            this.offered.remove(meal);
        }

    }



    public void Duplicate() {
        // TODO - to be completed
    }

    public List<String> mealsNames() {

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < this.meals.size(); i++) {
            result.add(this.meals.get(i).getName());
        }


        return result;
    }



    public int mealsSize() {
        return this.meals.size();
    }

    public Meal findMealByNameInMeals(String str) {
        for (int i = 0; i < this.meals.size(); i++) {
            if (this.meals.get(i).getName().equals(str)) {
                return this.meals.get(i);
            }
        }
        return null;
    }

    //endregion


}
