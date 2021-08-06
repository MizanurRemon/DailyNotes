package com.example.dailynotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dailynotes.Model.Notes.Notes_add_repositories;
import com.example.dailynotes.Model.Registration.Registration_repositories;

public class Add_note_ViewModel extends AndroidViewModel {
    public Add_note_ViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMessage(String userID,String title, String currentTime, String currentDate, String note){

        return Notes_add_repositories.getInstance().getMessage(userID, title, currentTime, currentDate, note);

    }
}
