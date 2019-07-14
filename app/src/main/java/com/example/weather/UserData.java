package com.example.weather;

public class UserData {
    private String MyUserName;
    private String MyPassword;


    public UserData(){

    }

    public UserData(String myUserName,String myPassword){
        MyUserName=myUserName;
        MyPassword=myPassword;
    }

    public String getMyUserName() {
        return MyUserName;
    }

    public void setMyUserName(String myUserName) {
        MyUserName = myUserName;
    }

    public String getMyPassword() {
        return MyPassword;
    }

    public void setMyPassword(String myPassword) {
        MyPassword = myPassword;
    }
}
