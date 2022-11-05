package com.example.meallogin;

public abstract class GeneralUser {
    private String username;
    private String password;
    private String email;
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
    public void setUsername(String username) {this.username = username; }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
