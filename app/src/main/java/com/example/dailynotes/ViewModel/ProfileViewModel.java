package com.example.dailynotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dailynotes.Model.Login.Login_repositories;
import com.example.dailynotes.Model.Login.Login_response;
import com.example.dailynotes.Model.Profile.Profile_repositories;
import com.example.dailynotes.Model.Profile.Profile_response;

public class ProfileViewModel extends AndroidViewModel {
    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Profile_response> getMessage(String userID) {

        return Profile_repositories.getInstance().getMessage(userID);

    }
}
