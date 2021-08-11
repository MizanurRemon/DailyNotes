package com.example.dailynotes.Model.Registration;

import com.example.dailynotes.Model.Notes.Notes_response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserCheck_API {

    @GET("/checkuser")
    Call<String> checkUser(@Query("phone") String phone);

}
