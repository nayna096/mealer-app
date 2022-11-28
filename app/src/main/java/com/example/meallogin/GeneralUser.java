package com.example.meallogin;

import java.io.Serializable;

public abstract class GeneralUser implements Serializable {
    private String username;
    private String password;
    private String email;
    private String role;
    private String address;

    public GeneralUser(){}
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
        return this.email;
    }
    public String getRole() {return role;}
    public String getAddress() {return address;}

    public void setUsername(String username) {this.username = username; }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {this.email = email;}
    public void setRole(String role) {this.role = role;}
    public void setAddress(String address) {this.address = address;}

}
