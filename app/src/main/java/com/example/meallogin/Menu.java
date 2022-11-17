package com.example.meallogin;

import java.io.Serializable;
import java.util.ArrayList;

public class Menu implements Serializable {
    private ArrayList<Meal> meallist;
    private ArrayList<Meal> offered;

    public Menu() {
    }

    public Menu(Cook c) {
        this.meallist = new ArrayList<Meal>();
        this.offered = new ArrayList<Meal>();
        c.setMenu(this);
    }

    public ArrayList<Meal> getMeallist() {
        return meallist;
    }

    public void setMeallist(ArrayList<Meal> menu) {
        this.meallist = menu;
    }

    public ArrayList<Meal> getOffered() {
        return offered;
    }

    public void setOffered(ArrayList<Meal> offered) {
        this.offered = offered;
    }

    public void addtoMeallist(Meal meal) {
        if (this.meallist.contains(meal) == false) {
            this.meallist.add(meal);
        }
    }

    public void addtoOffered(Meal meal) {
        if (this.meallist.contains(meal)&&this.offered.contains(meal)==false) {
            this.offered.add(meal);
        }
    }

    public void removefromMeallist(Meal meal) {
        this.meallist.remove(meal);
        if (this.offered.contains(meal)) {
            this.offered.remove(meal);
        }
    }

    public void removefromOffered(Meal meal) {
        this.offered.remove(meal);
    }
    public void Duplicate(){

    }
}
