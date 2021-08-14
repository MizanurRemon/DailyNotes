package com.example.dailynotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dailynotes.Model.Notes.Notes_repositories;
import com.example.dailynotes.Model.Notes.Notes_response;

import java.util.List;

public class Notes_ViewModel extends AndroidViewModel {
    public Notes_ViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Notes_response>> getNotes(String userID) {

        return Notes_repositories.getInstance().getMessage(userID);

    }

    public LiveData<Notes_response> getNotesData(String noteID) {

        return Notes_repositories.getInstance().getMessageData(noteID);

    }
}
