package com.example.meallogin;

public class Cook {

    private String username;
    private String password;
    private String role;
    private String email;

    public Cook(){}
    public Cook(String Username, String Password, String Email){
        this.username = Username;
        this.password = Password;
        this.role = "Cook";
        this.email = Email;
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
}
