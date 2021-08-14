package com.example.dailynotes.Model.Notes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Notes_API {
    @GET("/getnotes")
    Call<List<Notes_response>> getResponse(@Query("userID") String userID);
}
