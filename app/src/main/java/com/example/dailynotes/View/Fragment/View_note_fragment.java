package com.example.dailynotes.View.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailynotes.R;

public class View_note_fragment extends Fragment {

    TextView titleText, timeText, dateText, noteText;
    String title, time, date, note;
    ImageView backButton;

    public View_note_fragment(String title, String time, String date, String note) {
        this.title = title;
        this.time = time;
        this.date = date;
        this.note = note;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        main();
    }

    private void main() {
        titleText.setText(title);
        timeText.setText(time);
        dateText.setText(date);
        noteText.setText(note);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.view_note_fragment, container, false);

        titleText = (TextView) view.findViewById(R.id.titleTextID);
        timeText = (TextView) view.findViewById(R.id.timeTextID);
        dateText = (TextView) view.findViewById(R.id.dateTextID);
        noteText = (TextView) view.findViewById(R.id.noteID);

        backButton = (ImageView) view.findViewById(R.id.backButtonID);
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

        return view;
    }
}