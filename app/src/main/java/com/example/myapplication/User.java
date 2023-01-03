package com.example.myapplication;

import java.util.ArrayList;

public class User {

    public String username, email, password;
    public ArrayList<String> activityList;
    public String tripDate;
    public String destination;

    public User(String destination, String date, ArrayList<String> activityList) {
        this.destination = destination;
        this.tripDate = date;
        this.activityList = activityList;
    }
    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
