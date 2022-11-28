package com.example.meallogin;

public class Client  extends GeneralUser{
    public Client(){}
//    String Username,Password,Email,Role,address;
    public Client(String Username, String Password, String Email, String address){
        this.setUsername(Username);
        this.setPassword(Password);
        this.setRole("Client");
        this.setEmail(Email);
        this.setAddress(address);
    }

}
