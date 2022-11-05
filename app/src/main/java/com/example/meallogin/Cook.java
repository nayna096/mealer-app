package com.example.meallogin;

import java.io.Serializable;

public class Cook extends GeneralUser implements Serializable {


    private String role;

    private boolean status;
    public Cook(){}
    public Cook(String Username, String Password, String Email){
        this.username = Username;
        this.password = Password;
        this.role = "Cook";
        this.email = Email;
        this.status = false;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public void setRole(String role) {
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }


    public boolean isSuspended(){return this.status = true;}


}
