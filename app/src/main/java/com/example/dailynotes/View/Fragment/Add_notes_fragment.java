package com.example.dailynotes.View.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailynotes.R;
import com.example.dailynotes.ViewModel.Add_note_ViewModel;
import com.example.dailynotes.ViewModel.RegistrationViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class Add_notes_fragment extends Fragment {

    String userID;
    ImageView backButton;
    TextView addButton;
    TextView timeText, dateText;
    TextInputEditText titleText;
    EditText noteText;
    TextInputLayout titleError;
    String currentTime, currentDate;
    Add_note_ViewModel addNoteViewModel;
    Dialog loader;

    public Add_notes_fragment(String userID) {
        this.userID = userID;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        main();
    }

    private void main() {
        timeText.setText(currentTime);
        dateText.setText(currentDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.add_notes_fragment, container, false);

        addNoteViewModel = new ViewModelProvider(this).get(Add_note_ViewModel.class);

        timeText = (TextView) view.findViewById(R.id.timeTextID);
        dateText = (TextView) view.findViewById(R.id.dateTextID);
        addButton = (TextView) view.findViewById(R.id.addNotesButtonID);
        backButton = (ImageView) view.findViewById(R.id.backButtonID);
        titleText = (TextInputEditText) view.findViewById(R.id.titleTextID);
        noteText = (EditText) view.findViewById(R.id.noteTextID);

        titleError = (TextInputLayout) view.findViewById(R.id.titleErrorID);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                ).replace(R.id.frame_container, new Home_fragment()).commit();
            }
        });

        currentTime = new SimpleDateFormat("hh : mm a", Locale.getDefault()).format(Calendar.getInstance().getTime()).toLowerCase();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        currentDate = sdf.format(cal.getTime());

        loader = new Dialog(getActivity());
        loader.setContentView(R.layout.loader_alert);
        loader.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loader.setCancelable(false);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleText.getText().toString().trim();
                String note = noteText.getText().toString().trim();

                titleError.setErrorEnabled(false);
                if (TextUtils.isEmpty(title)) {
                    titleError.setError(" ");
                } else {
                    add_notes(title, note);
                }
            }
        });

        return view;
    }

    private void add_notes(String title, String note) {
        loader.show();
        addNoteViewModel.getMessage(userID, title, currentTime, currentDate, note).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                loader.dismiss();
                if (s.equals("inserted")) {
                    Toast.makeText(getActivity(), "Note added", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Home_fragment()).commit();

                } else {
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}