package com.example.meallogin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Serializable {
    private ArrayList<Meal> meallist;
    private ArrayList<Meal> offered;
    private String placeholder = "Empty"; //needed to keep Menu from becoming null
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

    public String getPlaceholder() {return placeholder;}

    //endregion

    //region Setters
    public void setMeallist(ArrayList<Meal> menu) {
        this.meallist = menu;
    }

    public void setOffered(ArrayList<Meal> offered) {
        this.offered = offered;
    }

    public void setPlaceholder(String placeholder) {this.placeholder = placeholder;}
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
            this.offered.remove(meal);
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

    public List<String> mealListNames() {

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < this.meallist.size(); i++) {
            result.add(this.meallist.get(i).getName());
        }


        return result;
    }

    public List<String> menuNames() {

        List<String> result = new ArrayList<String>();

        for (int i = 0; i < this.offered.size(); i++) {
            result.add(this.offered.get(i).getName());
        }


        return result;
    }

    public int mealListSize() {
        return this.meallist.size();
    }

    public int menuSize() {
        return this.offered.size();
    }

    public Meal findMealByNameInOffered(String str) {
        for (int i = 0; i < this.offered.size(); i++) {
            if (this.offered.get(i).getName().equals(str)) {
                return this.offered.get(i);
            }
        }
        return null;
    }
    public Meal findMealByNameInMeallist(String str) {
        for (int i = 0; i < this.meallist.size(); i++) {
            if (this.meallist.get(i).getName().equals(str)) {
                return this.meallist.get(i);
            }
        }
        return null;
    }

    public int findMealIndexInOffered(Meal meal) {
        for (int i = 0; i < this.offered.size(); i++) {
            if (this.offered.get(i).equals(meal)) {
                return i;
            }
        }
        return -1;
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
