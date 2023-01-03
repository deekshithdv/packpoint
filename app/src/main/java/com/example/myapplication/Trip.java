package com.example.myapplication;

public class Trip {
    public String activityList;
    public String tripDate;
    public String destination;

    public Trip (String destination, String date, String activityList) {
        this.destination = destination;
        this.tripDate = date;
        this.activityList = activityList;
    }
}
