package com.example.dailynotes.Model;

import com.example.dailynotes.Model.Login.Login_API;
import com.example.dailynotes.Model.Profile.Profile_API;
import com.example.dailynotes.Model.Registration.Registration_API;

public class APIUtilize {
    public APIUtilize() {
    }

    //public static final String BASE_URL = "http://192.168.0.104:1337";
    public static final String BASE_URL = "http://10.0.2.2:1337/";

    public static Registration_API registrationInterface(){
        return Retrofit_client.getClient(BASE_URL).create(Registration_API.class);
    }

    public static Login_API loginApi(){
        return Retrofit_client.getClient(BASE_URL).create(Login_API.class);
    }

    public static Profile_API profileApi(){
        return Retrofit_client.getClient(BASE_URL).create(Profile_API.class);
    }
}
