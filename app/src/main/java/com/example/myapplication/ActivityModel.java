package com.example.myapplication;


public class ActivityModel {

    private String activity_name;
    private int imgid;
    private String headerName;
    private boolean isChecked = false;

    public ActivityModel(String activity_name, int imgid) {
        this.activity_name = activity_name;
        this.imgid = imgid;
    }

    public String getActivity_name() {
        return activity_name;
    }
    public Boolean isChecked(){
        return isChecked;
    }
    public void isChecked(Boolean checked){
        this.isChecked = checked;
    }

    public void setActivty_name(String activty_name) {
        this.activity_name = activty_name;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
    public void setHeaderName(String headerName){
        this.headerName =  headerName;
    }
    public String getHeaderName(){
        return headerName;
    }
}
