package com.example.dailynotes.Model.Notes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Notes_add_API {

    @FormUrlEncoded
    @POST("/addnotes")
    Call<String> saveNote(@Field("userID") String userID,
                          @Field("title") String title,
                          @Field("time") String time,
                          @Field("date") String date,
                          @Field("note") String note);
}
