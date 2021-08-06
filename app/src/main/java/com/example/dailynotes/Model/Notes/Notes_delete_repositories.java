package com.example.dailynotes.Model.Notes;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.dailynotes.Model.APIUtilize;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notes_delete_repositories {
    private static Notes_delete_repositories notes_delete_repositories;
    Notes_delete_API notes_delete_api;
    MutableLiveData<String> message;

    private Notes_delete_repositories() {
        notes_delete_api = APIUtilize.notesDeleteApi();

    }

    public synchronized static Notes_delete_repositories getInstance() {
        if (notes_delete_repositories == null) {
            return new Notes_delete_repositories();
        }
        return notes_delete_repositories;
    }

    public @NonNull
    MutableLiveData<String> getMessage(@NonNull String noteID) {
        if (message == null) {
            message = new MutableLiveData<>();
        }
        Call<String> call = notes_delete_api.deleteNote(noteID);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    String notes_delete_response = response.body();
                    message.postValue(notes_delete_response);
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
