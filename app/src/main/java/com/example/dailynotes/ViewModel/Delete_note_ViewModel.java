package com.example.dailynotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dailynotes.Model.Notes.Notes_delete_repositories;

import java.util.List;

public class Delete_note_ViewModel extends AndroidViewModel {

    public Delete_note_ViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> deleteNotes(String noteID) {

        return Notes_delete_repositories.getInstance().getMessage(noteID);

    }
}
