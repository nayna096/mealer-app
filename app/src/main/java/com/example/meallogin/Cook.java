package com.example.meallogin;

import java.io.Serializable;

public class Cook extends GeneralUser implements Serializable {
    private boolean status;
    private Menu menu;
    private String suspensionDate;

    public Cook(){}
    
    public Cook(String Username, String Password, String Email){
        this.setUsername(Username);
        this.setPassword(Password);
        this.setRole("Cook");
        this.setEmail(Email);
        this.status = false;
        this.suspensionDate = null;
        this.menu = new Menu(this);
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isSuspended(){return this.status = true;}

    public void setMenu(Menu menu){this.menu = menu;}

    public void setSuspensionDate(String date){
        this.suspensionDate = date;
    }

    public String getSuspensionDate(){
        return this.suspensionDate;
    }

    public Menu getMenu(){return this.menu;}
    //Gonna create a Menu class for each cook, will allow for further differentiation between general users
}
