package com.example.dailynotes.Model.Notes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Notes_edit_API {
    @FormUrlEncoded
    @POST("/updatenotes")
    Call<String> updateNote(@Field("noteID") String noteID,
                          @Field("title") String title,
                          @Field("note") String note);
}
