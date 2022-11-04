package com.example.meallogin;

import java.io.Serializable;

public class Cook implements Serializable {

    private String username;
    private String password;
    private String role;
    private String email;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public boolean isSuspended(){return this.status = true;}


}
