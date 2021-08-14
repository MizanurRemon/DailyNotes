package com.example.dailynotes.Model.Notes;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.dailynotes.Model.APIUtilize;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notes_edit_repositories {
    private static Notes_edit_repositories notes_edit_repositories;
    Notes_edit_API notes_edit_api;
    MutableLiveData<String> message;

    private Notes_edit_repositories() {
        notes_edit_api = APIUtilize.notesEditApi();

    }

    public synchronized static Notes_edit_repositories getInstance() {
        if (notes_edit_repositories == null) {
            return new Notes_edit_repositories();
        }
        return notes_edit_repositories;
    }

    public @NonNull
    MutableLiveData<String> editNotes(@NonNull String noteID, @NonNull String title, @NonNull String note) {
        if (message == null) {
            message = new MutableLiveData<>();
        }
        Call<String> call = notes_edit_api.updateNote(noteID, title, note);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    String notes_edit_response = response.body();
                    message.postValue(notes_edit_response);
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
