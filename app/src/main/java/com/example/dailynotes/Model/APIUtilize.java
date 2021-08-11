package com.example.dailynotes.Model;

import android.provider.ContactsContract;

import com.example.dailynotes.Model.Login.Login_API;
import com.example.dailynotes.Model.Notes.Notes_API;
import com.example.dailynotes.Model.Notes.Notes_add_API;
import com.example.dailynotes.Model.Notes.Notes_delete_API;
import com.example.dailynotes.Model.Notes.Notes_delete_repositories;
import com.example.dailynotes.Model.Profile.Profile_API;
import com.example.dailynotes.Model.Registration.Registration_API;
import com.example.dailynotes.Model.Registration.UserCheck_API;

public class APIUtilize {
    public APIUtilize() {
    }

    public static final String BASE_URL = "http://10.0.2.2:1337/";

    public static UserCheck_API userCheckApi(){
        return Retrofit_client.getClient(BASE_URL).create(UserCheck_API.class);
    }

    public static Registration_API registrationInterface() {
        return Retrofit_client.getClient(BASE_URL).create(Registration_API.class);
    }

    public static Login_API loginApi() {
        return Retrofit_client.getClient(BASE_URL).create(Login_API.class);
    }

    public static Profile_API profileApi() {
        return Retrofit_client.getClient(BASE_URL).create(Profile_API.class);
    }

    public static Notes_API notesApi() {
        return Retrofit_client.getClient(BASE_URL).create(Notes_API.class);
    }

    public static Notes_add_API notesAddApi(){
        return Retrofit_client.getClient(BASE_URL).create(Notes_add_API.class);
    }

    public static Notes_delete_API notesDeleteApi(){
        return Retrofit_client.getClient(BASE_URL).create(Notes_delete_API.class);
    }
}
