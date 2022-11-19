package com.example.meallogin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cook extends GeneralUser implements Serializable {
    private boolean status;
    private Menu menu;
    private List<Meal> mealList;
    private String suspensionDate;

    String Username;
    String Password;
    String Role;
    String email;


    //region Constructors
    public Cook(){}
    
    public Cook(String Username, String Password, String Email){
        this.Username = Username;
        this.Password = Password;
        this.Role = "Cook";
        this.email = Email;
        this.status = false;
        this.suspensionDate = null;
        this.menu = new Menu(this);
    }
    //endregion

    //region Getters
    public boolean getStatus() {
        return status;
    }

    public String getSuspensionDate(){
        return this.suspensionDate;
    }

    public List<Meal> getMealList() {

        return mealList;
    }

    public Menu getMenu(){return this.menu;}
    //Gonna create a Menu class for each cook, will allow for further differentiation between general users

    public List<String> getMealListNames() {

        List<String> result = new ArrayList<>();

        for (int i=0; i<mealList.size(); i++) {
            result.add(mealList.get(i).getName());
        }
        return result;
    }

    public String[] getMenuNames() {

        String[] result = new String[menu.getMenuSize()];

        for (int i=0; i<menu.getMenuSize(); i++) {
            result[i] = menu.getMealAtIndex(i).getName();
        }

        return result;
    }

    public int getMealListSize() {
        return mealList.size();
    }

    public int getMenuSize() {
        return menu.getMenuSize();
    }

    public Meal getMealByName(String str) {
        for (int i=0; i<mealList.size(); i++) {
            if (mealList.get(i).getName().equals(str)) {
                return mealList.get(i);
            }
        }
        return null;
    }

    public int getMealIndexInMealList(Meal meal) {
        for (int i=0; i<mealList.size(); i++) {
            if (mealList.get(i).equals(meal)) {
                return i;
            }
        }
        return -1;
    }
    //endregion

    //region Setters
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMenu(Menu menu){this.menu = menu;}

    public void setSuspensionDate(String date){
        this.suspensionDate = date;
    }
    //endregion

    public void addToMealList(Meal meal) {
        mealList.add(meal);
    }

    public void deleteFromMealList(Meal meal) {
        mealList.remove(meal);
    }

    public boolean isSuspended(){return this.status = true;}
}
