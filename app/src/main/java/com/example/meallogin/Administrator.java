package com.example.meallogin;

import java.util.ArrayList;

public class Administrator  extends GeneralUser{
//    private String role;
//    private String email;
    private ArrayList<Complaint> complaints;
    public Administrator(){
        this.setUsername("admin");
        this.setPassword("admin");
        this.setRole("Administrator");
        this.setEmail("admin");
    }
//    public Administrator(String Username, String Password, String Email){
//        this.setUsername(Username);
//        this.setPassword(Password);
//        this.email = Email;
//
//    }

    public ArrayList<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(ArrayList<Complaint> complaints) {
        this.complaints = complaints;
    }

    public void addComplaint(Complaint complaint){
        complaints.add(complaint);
    }
}
