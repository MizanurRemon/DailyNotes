package com.example.dailynotes.Model.Profile;

import com.example.dailynotes.Model.Login.Login_response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Profile_API {
    @GET("/profile")
    Call<Profile_response> getResponse(@Query("userID") String userID);
}
