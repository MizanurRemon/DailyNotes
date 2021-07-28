package com.example.dailynotes.Model.Registration;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.dailynotes.Model.APIUtilize;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration_repositories {

    private static Registration_repositories registration_repositories;
    Registration_API registration_api;
    MutableLiveData<String> message;

    private Registration_repositories() {
        registration_api = APIUtilize.registrationInterface();

    }

    public synchronized static Registration_repositories getInstance() {
        if (registration_repositories == null) {
            return new Registration_repositories();
        }
        return registration_repositories;
    }

    public @NonNull
    MutableLiveData<String> getMessage(@NonNull String name, @NonNull String phone, @NonNull String mail, @NonNull String password) {
        if (message == null) {
            message = new MutableLiveData<>();
        }
        Call<String> call = registration_api.saveUser(name, phone, mail, password);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    String registration_response = response.body();
                    message.postValue(registration_response);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                message.postValue(t.getMessage());
            }

        });
        return message;
    }


}
