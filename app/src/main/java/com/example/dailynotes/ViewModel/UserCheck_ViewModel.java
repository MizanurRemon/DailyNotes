package com.example.dailynotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dailynotes.Model.Notes.Notes_repositories;
import com.example.dailynotes.Model.Notes.Notes_response;
import com.example.dailynotes.Model.Registration.UserCheck_repositories;
import com.example.dailynotes.Model.Registration.UserCheck_response;

import java.util.List;

public class UserCheck_ViewModel extends AndroidViewModel {
    public UserCheck_ViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> userCheck(String phone) {

        return UserCheck_repositories.getInstance().userCheck(phone);

    }
}
