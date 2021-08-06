package com.example.dailynotes.Model.Notes;

import android.util.Log;
import android.widget.Toast;

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
    MutableLiveData<List<Notes_response>> message;

    private Notes_repositories() {
        notesApi = APIUtilize.notesApi();
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
                    List<Notes_response> notes_response = response.body();
                    Log.d("success", "success");
                    message.postValue(notes_response);
                }
            }

            @Override
            public void onFailure(Call<List<Notes_response>> call, Throwable t) {
                List<Notes_response> response = new ArrayList<>();
                Log.d("error", t.toString());
                message.postValue(response);
            }
        });
        return message;
    }
}
