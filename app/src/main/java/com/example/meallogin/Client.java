package com.example.meallogin;

public class Client  extends GeneralUser{
    public Client(){}
    String Username,Password,Email,Role,address;
    public Client(String Username, String Password, String Email, String address){
        this.Username = Username;
        this.Password = Password;
        this.Role = "Client";
        this.Email = Email;
        this.address = address;
    }

}
