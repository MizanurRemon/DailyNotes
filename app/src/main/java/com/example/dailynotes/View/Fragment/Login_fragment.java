package com.example.dailynotes.View.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailynotes.Model.APIUtilize;
import com.example.dailynotes.Model.Login.Login_API;
import com.example.dailynotes.Model.Login.Login_response;
import com.example.dailynotes.Model.Session_Management;
import com.example.dailynotes.R;
import com.example.dailynotes.ViewModel.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class Login_fragment extends Fragment {

    TextView registerButton;
    AppCompatButton signInButton;
    TextInputEditText phoneText, passwordText;
    TextInputLayout phoneError, passwordError;

    LoginViewModel loginViewModel;
    Login_API loginApi;
    String id;
    Session_Management session_management;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        session_management = new Session_Management(getActivity());
        String userID = session_management.getSession();

        if(!userID.equals("-1")){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Home_fragment()).addToBackStack(null).commit();
        }

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginApi = APIUtilize.loginApi();

        registerButton = (TextView) view.findViewById(R.id.registerButtonID);
        signInButton = (AppCompatButton) view.findViewById(R.id.signInButtonID);
        phoneText = (TextInputEditText) view.findViewById(R.id.phoneTextID);
        passwordText = (TextInputEditText) view.findViewById(R.id.passwordTextID);

        phoneError = (TextInputLayout) view.findViewById(R.id.phoneErrorID);
        passwordError = (TextInputLayout) view.findViewById(R.id.passwordErrorID);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                ).replace(R.id.frame_container, new Registration_fragment()).addToBackStack(null).commit();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();

                phoneError.setErrorEnabled(false);
                passwordError.setErrorEnabled(false);

                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    if (TextUtils.isEmpty(phone)) {
                        phoneError.setError(" ");
                    } else if (TextUtils.isEmpty(password)) {
                        passwordError.setError(" ");
                    }
                } else {
                    sendData(phone, password);
                }
            }
        });

        return view;
    }

    private void sendData(String phone, String password) {

        loginViewModel.getMessage(phone, password).observe(Login_fragment.this, new Observer<Login_response>() {
            @Override
            public void onChanged(Login_response login_response) {

                try {
                    id = login_response.getUserID();
                } catch (Exception err) {
                    id = "-1";
                }


                if (id.equals("-1")) {
                    Toast.makeText(getActivity(), "Invalid phone/password", Toast.LENGTH_SHORT).show();
                    session_management.saveSession(id);
                } else {

                    session_management.saveSession(id);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Home_fragment()).addToBackStack(null).commit();

                }

            }
        });

    }
}