package com.example.meallogin;

public class Client  extends GeneralUser{
    private String role;
    public Client(){}
    public Client(String Username, String Password, String Email){
        this.username = Username;
        this.password = Password;
        this.role = "Client";
        this.email = Email;
    }

}
