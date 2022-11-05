package com.example.meallogin;

public class Administrator  extends GeneralUser{
    private String role;
    private String email;
    public Administrator(){}
    public Administrator(String Username, String Password, String Email){
        this.username = Username;
        this.password = Password;
        this.role = "Administrator";
        this.email = Email;

    }
}
