package com.example.dailynotes.Model.Notes;

import com.example.dailynotes.Model.Profile.Profile_response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Notes_individual_API {

    @GET("/getindividualnotes")
    Call<Notes_response> getResponse(@Query("noteID") String noteID);

}
