package com.example.dailynotes.Model.Registration;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Registration_API {

    @FormUrlEncoded
    @POST("/registration")
    Call<String> saveUser(@Field("name") String name,
                          @Field("phone") String phone,
                          @Field("mail") String mail,
                          @Field("password") String password);
}
