package com.example.meallogin;

public abstract class GeneralUser {
    private String username;
    private String password;
    private String role;
    private String email;
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRole(){
        return this.role;
    }
    public String getEmail(){
        return this.email;
    }
}
