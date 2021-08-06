package com.example.dailynotes.Model.Notes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Notes_delete_API {
    @FormUrlEncoded
    @POST("/deletenotes")
    Call<String> deleteNote(@Field("noteID") String noteID);
}
