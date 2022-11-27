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
        this.complaints = null;
    }

    public ArrayList<Complaint> getComplaints() {
        return complaints;
    }
    public void addComplaint(Complaint complaint){
        this.complaints.add(complaint);
    }

    //    public Administrator(String Username, String Password, String Email){
//        this.setUsername(Username);
//        this.setPassword(Password);
//        this.email = Email;
//
//    }

}
