package com.example.meallogin;

import java.io.Serializable;

public class Cook extends GeneralUser implements Serializable {
    private boolean status;
    private Menu menu;
    private Meal[] mealList;
    private String suspensionDate;

    private int mealListIndex;

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
        this.mealList = new Meal[100];
        mealListIndex = 0;
    }
    //endregion

    //region Getters
    public boolean getStatus() {
        return status;
    }

    public String getSuspensionDate(){
        return this.suspensionDate;
    }

    public Meal[] getMealList() {

        Meal[] result = new Meal[mealListIndex];

        for (int i=0; i<mealListIndex; i++) {
            result[i] = mealList[i];
        }

        return result;
    }

    public Menu getMenu(){return this.menu;}
    //Gonna create a Menu class for each cook, will allow for further differentiation between general users

    public String[] getMealListNames() {

        String[] result = new String[mealListIndex];

        for (int i=0; i<mealListIndex; i++) {
            result[i] = mealList[i].getName();
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
        return mealListIndex;
    }

    public int getMenuSize() {
        return menu.getMenuSize();
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
        mealList[mealListIndex] = meal;
        mealListIndex++;
    }

    public boolean isSuspended(){return this.status = true;}
}
