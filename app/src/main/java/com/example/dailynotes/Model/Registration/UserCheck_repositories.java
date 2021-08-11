package com.example.dailynotes.Model.Registration;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dailynotes.Model.APIUtilize;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserCheck_repositories {

    private static UserCheck_repositories userCheck_repositories;
    UserCheck_API userCheckApi;
    MutableLiveData<String> message;

    private UserCheck_repositories() {
        userCheckApi = APIUtilize.userCheckApi();

    }

    public synchronized static UserCheck_repositories getInstance() {
        if (userCheck_repositories == null) {
            return new UserCheck_repositories();
        }
        return userCheck_repositories;
    }

    public @NonNull
    MutableLiveData<String> userCheck( @NonNull String phone) {
        if (message == null) {
            message = new MutableLiveData<>();
        }
        Call<String> call = userCheckApi.checkUser(phone);

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
