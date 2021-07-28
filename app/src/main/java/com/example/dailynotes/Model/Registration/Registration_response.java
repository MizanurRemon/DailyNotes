package com.example.dailynotes.Model.Registration;

import com.google.gson.annotations.SerializedName;

public class Registration_response {

    @SerializedName("message")
    private String message;

    public Registration_response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
