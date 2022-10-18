package com.example.meallogin;

public class Cook {
    private String username;
    private String password;
    private String role;
    private String email;
    public Cook(String Username, String Password, String Email){
        this.username = Username;
        this.password = Password;
        this.role = "Cook";
        this.email = Email;
    }
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
