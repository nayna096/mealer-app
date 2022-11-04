package com.example.meallogin;

public class Complaint {
    private String description;
    private String severity;
    private Cook cook;
    public Complaint(){}

    public Complaint(String description, Cook cook){
        this.description = description;
        this.cook = cook;
        this.severity = "unknown";
    }

    public void setSeverity(String severity) {this.severity = severity;}

    public String getSeverity() {return severity;}

    public void setDescription(String description) {this.description = description;}

    public String getDescription() {return description;}

    public Cook getCook() {return cook;}

    public void setCook(Cook cook) {this.cook = cook;}
}
