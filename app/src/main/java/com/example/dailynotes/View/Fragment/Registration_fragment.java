package com.example.dailynotes.View.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dailynotes.Model.APIUtilize;
import com.example.dailynotes.Model.Registration.Registration_API;
import com.example.dailynotes.R;
import com.example.dailynotes.ViewModel.RegistrationViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Registration_fragment extends Fragment {
    ImageView backButton;
    TextInputEditText nameText, phoneText, mailText, passwordText, rePasswordText;
    TextInputLayout nameError, phoneError, mailError, passwordError, rePasswordError;
    AppCompatButton submitButton;
    Registration_API registration_interface;
    Dialog loaderDialog;
    RegistrationViewModel registrationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.registration_fragment, container, false);

        registration_interface = APIUtilize.registrationInterface();
        registrationViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);

        loaderDialog = new Dialog(getActivity());
        loaderDialog.setContentView(R.layout.loader_alert);
        loaderDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loaderDialog.setCancelable(false);

        backButton = (ImageView) view.findViewById(R.id.backButtonID);

        nameText = (TextInputEditText) view.findViewById(R.id.nameTextID);
        phoneText = (TextInputEditText) view.findViewById(R.id.phoneTextID);
        mailText = (TextInputEditText) view.findViewById(R.id.mailTextID);
        passwordText = (TextInputEditText) view.findViewById(R.id.passwordTextID);
        rePasswordText = (TextInputEditText) view.findViewById(R.id.retypePasswordTextID);

        nameError = (TextInputLayout) view.findViewById(R.id.nameErrorID);
        phoneError = (TextInputLayout) view.findViewById(R.id.phoneErrorID);
        mailError = (TextInputLayout) view.findViewById(R.id.mailErrorID);
        passwordError = (TextInputLayout) view.findViewById(R.id.passwordErrorID);
        rePasswordError = (TextInputLayout) view.findViewById(R.id.retypePasswordErrorID);

        submitButton = (AppCompatButton) view.findViewById(R.id.submitButtonID);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString().trim();
                String phone = phoneText.getText().toString().trim();
                String mail = mailText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();
                String rePassword = rePasswordText.getText().toString().trim();


                validation(name, phone, mail, password, rePassword);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Login_fragment()).commit();
            }
        });

        return view;
    }

    private void validation(String name, String phone, String mail, String password, String rePassword) {
        nameError.setErrorEnabled(false);
        phoneError.setErrorEnabled(false);
        mailError.setErrorEnabled(false);
        passwordError.setErrorEnabled(false);
        rePasswordError.setErrorEnabled(false);

        if (TextUtils.isEmpty(name)) {
            nameError.setError(" ");
        } else if (TextUtils.isEmpty(phone)) {
            phoneError.setError(" ");
        } else if (TextUtils.isEmpty(mail)) {
            mailError.setError(" ");
        } else if (TextUtils.isEmpty(password) || password.length() < 6) {

            if (TextUtils.isEmpty(password)) {
                passwordError.setError(" ");
            } else if (password.length() < 6) {
                passwordError.setError("Password must be 6 digit");
            }

        } else if (TextUtils.isEmpty(rePassword)) {
            rePasswordError.setError(" ");
        } else {

            if (!password.equals(rePassword)) {
                rePasswordError.setError("Not match");
            } else {
                loaderDialog.show();
                registration(name, phone, mail, password);
            }

        }
    }

    private void registration(String name, String phone, String mail, String password) {
        // code
        registrationViewModel.getMessage(name, phone, mail, password).observe(Registration_fragment.this, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                loaderDialog.dismiss();
                if (s.equals("Registered")) {
                    Toast.makeText(getActivity(), "Registered", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Login_fragment()).commit();
                } else {
                    Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}