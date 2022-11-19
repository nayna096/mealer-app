package com.example.meallogin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable {
    private ArrayList<Meal> meallist;
    private ArrayList<Meal> offered;
    private String emptyMessage = "Would you like to add some meals?";

    //region Constructors


    public Menu() {
        this.meallist = new ArrayList<Meal>();
        this.offered = new ArrayList<Meal>();
    }
    //endregion

    //region Getters
    public ArrayList<Meal> getMeallist() {
        return meallist;
    }

    public ArrayList<Meal> getOffered() {
        return offered;
    }

    public String getEmptyMessage() {return emptyMessage;}
    //endregion

    //region Setters
    public void setMeallist(ArrayList<Meal> menu) {
        this.meallist = menu;
    }

    public void setOffered(ArrayList<Meal> offered) {
        this.offered = offered;
    }

    public void setEmptyMessage(String emptyMessage) {this.emptyMessage = "Would you like to add some meals?";}
    //endregion

    //region non-suspended cook only
    public void addtoMeallist(Meal meal) {
        if (!(this.meallist.contains(meal))) {
            this.meallist.add(meal);
        }
    }

    public void deletefromMeallist(Meal meal) {
        if (this.meallist.contains(meal)) {
            this.meallist.remove(meal);
        }

    }

    public void addtoOffered(Meal meal) {
        if (this.meallist.contains(meal) && !(this.offered.contains(meal))) {
            this.offered.add(meal);
        }
    }

    public void removefromOffered(Meal meal) {
        if (this.offered.contains(meal)) {
            this.offered.remove(meal);
        }
    }

    public void Duplicate() {
        // TODO - to be completed
    }

    public void removefromMeallist(Meal meal) {
        this.meallist.remove(meal);
        if (this.offered.contains(meal)) {
            this.offered.remove(meal);
        }
    }

    public List<String> mealListNames() {

        List<String> result = new ArrayList<String>();
        if (this.meallist.size() > 0) {
            for (int i = 0; i < this.meallist.size(); i++) {
                result.add(this.meallist.get(i).getName());
            }


        } else {
            result.add(emptyMessage);
        }
        return result;
    }

    public List<String> menuNames() {

        List<String> result = new ArrayList<String>();
        if (this.offered.size() > 0) {
            for (int i = 0; i < this.offered.size(); i++) {
                result.add(this.offered.get(i).getName());
            }
        } else {
            result.add(emptyMessage);
        }

        return result;
    }

    public int mealListSize() {
        return this.meallist.size();
    }

    public int menuSize() {
        return this.offered.size();
    }

    public Meal findMealByName(String str) {
        for (int i = 0; i < this.meallist.size(); i++) {
            if (this.meallist.get(i).getName().equals(str)) {
                return this.meallist.get(i);
            }
        }
        return null;
    }

    public int findMealIndexInMealList(Meal meal) {
        for (int i = 0; i < this.meallist.size(); i++) {
            if (this.meallist.get(i).equals(meal)) {
                return i;
            }
        }
        return -1;
    }
    //endregion


}
