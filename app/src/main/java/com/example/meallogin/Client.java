package com.example.meallogin;

public class Client {
    private String username;
    private String password;
    private String role;
    private String email;
    public Client(){}
    public Client(String Username, String Password, String Email){
        this.username = Username;
        this.password = Password;
        this.role = "Client";
        this.email = Email;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRole(){return this.role;}
    public String getEmail(){
        return this.email;
    }
}
