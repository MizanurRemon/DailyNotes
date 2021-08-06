package com.example.dailynotes.Model.Notes;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.dailynotes.Model.APIUtilize;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notes_add_repositories {

    private static Notes_add_repositories notes_add_repositories;
    Notes_add_API notes_add_api;
    MutableLiveData<String> message;

    private Notes_add_repositories() {
        notes_add_api = APIUtilize.notesAddApi();

    }

    public synchronized static Notes_add_repositories getInstance() {
        if (notes_add_repositories == null) {
            return new Notes_add_repositories();
        }
        return notes_add_repositories;
    }

    public @NonNull
    MutableLiveData<String> getMessage(@NonNull String userID,@NonNull String title, @NonNull String currentTime, @NonNull String currentDate, @NonNull String note) {
        if (message == null) {
            message = new MutableLiveData<>();
        }
        Call<String> call = notes_add_api.saveNote(userID, title, currentTime, currentDate, note);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    String notes_add_response = response.body();
                    message.postValue(notes_add_response);
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
