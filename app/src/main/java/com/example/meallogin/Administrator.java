package com.example.meallogin;

public class Administrator  extends GeneralUser{
//    private String role;
    private String email;
    public Administrator(){}
    public Administrator(String Username, String Password, String Email){
        this.setUsername(Username);
        this.setPassword(Password);
        this.setRole("Administrator");
        this.email = Email;

    }

}
