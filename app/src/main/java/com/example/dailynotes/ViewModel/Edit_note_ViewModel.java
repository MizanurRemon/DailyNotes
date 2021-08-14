package com.example.dailynotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dailynotes.Model.Notes.Notes_add_repositories;
import com.example.dailynotes.Model.Notes.Notes_edit_repositories;

public class Edit_note_ViewModel extends AndroidViewModel {
    public Edit_note_ViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMessage(String noteID, String title, String note) {

        return Notes_edit_repositories.getInstance().editNotes(noteID, title, note);

    }
}
