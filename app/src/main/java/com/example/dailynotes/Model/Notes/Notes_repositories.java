package com.example.dailynotes.Model.Notes;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.dailynotes.Model.APIUtilize;
import com.example.dailynotes.Model.Profile.Profile_response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notes_repositories {

    public static Notes_repositories notes_repositories;
    Notes_API notesApi;
    Notes_individual_API notesIndividualApi;
    MutableLiveData<List<Notes_response>> message;
    MutableLiveData<Notes_response> messageData;

    private Notes_repositories() {
        notesApi = APIUtilize.notesApi();
        notesIndividualApi = APIUtilize.notesIndividualApi();
    }

    public synchronized static Notes_repositories getInstance() {
        if (notes_repositories == null) {
            return new Notes_repositories();
        }
        return notes_repositories;
    }

    public @NonNull
    MutableLiveData<List<Notes_response>> getMessage(@NonNull String userID) {

        if (message == null) {
            message = new MutableLiveData<>();
        }

        Call<List<Notes_response>> call = notesApi.getResponse(userID);

        call.enqueue(new Callback<List<Notes_response>>() {
            @Override
            public void onResponse(Call<List<Notes_response>> call, Response<List<Notes_response>> response) {

                if (response.isSuccessful()) {
                    List<Notes_response> notes_ID_response = response.body();
                    message.postValue(notes_ID_response);
                }
            }

            @Override
            public void onFailure(Call<List<Notes_response>> call, Throwable t) {
                List<Notes_response> response = new ArrayList<>();
                message.postValue(response);
            }
        });
        return message;
    }

    public @NonNull
    MutableLiveData<Notes_response> getMessageData(@NonNull String noteID) {

        if (messageData == null) {
            messageData = new MutableLiveData<>();
        }

        Call<Notes_response> call = notesIndividualApi.getResponse(noteID);

        call.enqueue(new Callback<Notes_response>() {
            @Override
            public void onResponse(Call<Notes_response> call, Response<Notes_response> response) {

                if (response.isSuccessful()) {
                    Notes_response n_response = response.body();
                    messageData.postValue(n_response);
                    Log.d("ErrorXXX", "gtacvjscv");
                }
            }

            @Override
            public void onFailure(Call<Notes_response> call, Throwable t) {
                Notes_response response = new Notes_response();
                messageData.postValue(response);
                Log.d("ErrorXXX", t.getMessage());
            }
        });
        return messageData;
    }

}
