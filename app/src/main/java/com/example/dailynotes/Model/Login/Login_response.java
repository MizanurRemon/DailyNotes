package com.example.dailynotes.Model.Login;

import com.google.gson.annotations.SerializedName;

public class Login_response {
    @SerializedName("userID")
    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
