package com.example.dailynotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dailynotes.Model.Registration.Registration_repositories;

public class RegistrationViewModel extends AndroidViewModel {


    public RegistrationViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMessage(String name, String phone, String mail, String password){

        return Registration_repositories.getInstance().getMessage(name, phone, mail, password);

    }
}
