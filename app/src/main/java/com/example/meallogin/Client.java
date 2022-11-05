package com.example.meallogin;

public class Client  extends GeneralUser{
    public Client(){}
    public Client(String Username, String Password, String Email){
        this.setUsername(Username);
        this.setPassword(Password);
        this.setRole("Client");
        this.setEmail(Email);
    }

}
