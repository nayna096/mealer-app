package com.example.meallogin;

import java.io.Serializable;
import java.util.ArrayList;

public class Menu implements Serializable {
    private ArrayList<Meal> meallist;
    private ArrayList<Meal> offered;

    //region Constructors
    public Menu() {
    }

    public Menu(Cook c) {
        this.meallist = new ArrayList<Meal>();
        this.offered = new ArrayList<Meal>();
        c.setMenu(this);
    }
    //endregion

    //region Getters
    public ArrayList<Meal> getMeallist() {
        return meallist;
    }
    public ArrayList<Meal> getOffered() {
        return offered;
    }
    public int getMenuSize() {
        return offered.size();
    }
    public Meal getMealAtIndex(int i) {
        return offered.get(i);
    }
    //endregion

    //region Setters
    public void setMeallist(ArrayList<Meal> menu) {
        this.meallist = menu;
    }
    public void setOffered(ArrayList<Meal> offered) {
        this.offered = offered;
    }
    //endregion

    //region non-suspended cook only
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

    public void removefromOffered(Meal meal) {
        this.offered.remove(meal);
    }

    public void Duplicate(){
        // TODO - to be completed
    }

    public void removefromMeallist(Meal meal) {
        this.meallist.remove(meal);
        if (this.offered.contains(meal)) {
            this.offered.remove(meal);
        }
    }
    //endregion


}
