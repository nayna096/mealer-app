package com.example.meallogin;

public class Complaint {
    private String description;
    private String suspensionDate;
    private String cookUsername;
    private Boolean actioned;
    public Complaint(){}

    public Complaint(String description, String cookUsername){
        this.description = description;
        this.cookUsername = cookUsername;
        this.suspensionDate = "unknown";
        this.actioned = false;
    }

    public void setSuspensionDate(String severity) {this.suspensionDate = severity;}

    public String getSuspensionDate() {return suspensionDate;}

    public void setDescription(String description) {this.description = description;}

    public String getDescription() {return description;}

    public String getCookUsername() {return cookUsername;}

    public void setCookUsername(String cookUsername) {this.cookUsername = cookUsername;}

    public boolean getActioned(){
        return this.actioned;
    }
    public void action(){
        this.actioned = true;
    }
}
