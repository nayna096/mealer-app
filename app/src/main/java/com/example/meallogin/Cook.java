package com.example.meallogin;

import java.io.Serializable;

public class Cook extends GeneralUser implements Serializable {
    private boolean status;
    public Cook(){}
    public Cook(String Username, String Password, String Email){
        this.setUsername(Username);
        this.setPassword(Password);
        this.setRole("Cook");
        this.setEmail(Email);
        this.status = false;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isSuspended(){return this.status = true;}


}
