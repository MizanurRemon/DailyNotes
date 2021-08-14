package com.example.dailynotes.View.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailynotes.Model.Notes.Notes_response;
import com.example.dailynotes.R;
import com.example.dailynotes.ViewModel.Notes_ViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.Objects;

public class View_note_fragment extends Fragment {

    TextView titleText, timeText, dateText, noteText;
    String noteID, title, time, date, note;
    ;
    ImageView backButton;
    ExtendedFloatingActionButton editNotesButton;
    Notes_ViewModel notesViewModel;

    public View_note_fragment(String noteID) {
        this.noteID = noteID;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        main();

        editNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                ).replace(R.id.frame_container, new Edit_notes_fragment(noteID, title, note)).addToBackStack(null).commit();
            }
        });
    }

    private void main() {

        notesViewModel.getNotesData(noteID).observe(getViewLifecycleOwner(), new Observer<Notes_response>() {
            @Override
            public void onChanged(Notes_response notes_response) {

                title = notes_response.getTitle();
                time = notes_response.getTime();
                date = notes_response.getDate();
                note = notes_response.getNote();

                titleText.setText(title);
                timeText.setText(time);
                dateText.setText(date);
                noteText.setText(note);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.view_note_fragment, container, false);

        notesViewModel = new ViewModelProvider(this).get(Notes_ViewModel.class);

        titleText = (TextView) view.findViewById(R.id.titleTextID);
        timeText = (TextView) view.findViewById(R.id.timeTextID);
        dateText = (TextView) view.findViewById(R.id.dateTextID);
        noteText = (TextView) view.findViewById(R.id.noteID);

        backButton = (ImageView) view.findViewById(R.id.backButtonID);
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        ).replace(R.id.frame_container, new Home_fragment()).commit());

        editNotesButton = (ExtendedFloatingActionButton) view.findViewById(R.id.editNotesButtonID);

        return view;
    }
}