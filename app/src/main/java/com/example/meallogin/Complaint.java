package com.example.meallogin;
import java.io.Serializable;
public class Complaint implements Serializable {
    private String description;
    private String suspensionDate;
    private Cook cookUsername;
    private Boolean actioned;
    public Complaint(){}

    public Complaint(String description, Cook cookUsername){
        this.description = description;
        this.cookUsername = cookUsername;
        this.suspensionDate = "unknown";
        this.actioned = false;
    }





    public void setSuspensionDate(String severity) {this.suspensionDate = severity;}

    public String getSuspensionDate() {return suspensionDate;}

    public void setDescription(String description) {this.description = description;}

    public String getDescription() {return description;}

    public Cook getCook() {return cookUsername;}

    public void setCookUsername(Cook cookUsername) {this.cookUsername = cookUsername;}
    public Cook getCookUsername() {
        return cookUsername;
    }
    public void setActioned(Boolean actioned) {
        this.actioned = actioned;
    }
    public boolean getActioned(){
        return this.actioned;
    }
    public void action(){
        this.actioned = true;
    }
}
