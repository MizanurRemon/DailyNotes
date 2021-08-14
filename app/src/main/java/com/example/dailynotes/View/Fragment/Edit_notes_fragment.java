package com.example.dailynotes.View.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailynotes.R;
import com.example.dailynotes.ViewModel.Edit_note_ViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Edit_notes_fragment extends Fragment {
    ImageView backButton;
    String noteID, title, note;
    TextInputEditText titleText;
    TextInputLayout titleError;
    EditText noteText;
    TextView updateNotesButton;
    Edit_note_ViewModel editNoteViewModel;
    Dialog loader;

    public Edit_notes_fragment(String noteID, String title, String note) {
        this.noteID = noteID;
        this.title = title;
        this.note = note;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.edit_notes_fragment, container, false);

        editNoteViewModel = new ViewModelProvider(this).get(Edit_note_ViewModel.class);
        backButton = (ImageView) view.findViewById(R.id.backButtonID);
        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        ).replace(R.id.frame_container, new View_note_fragment(noteID)).commit());

        titleError = (TextInputLayout) view.findViewById(R.id.titleErrorID);
        titleText = (TextInputEditText) view.findViewById(R.id.titleTextID);
        noteText = (EditText) view.findViewById(R.id.noteTextID);
        updateNotesButton = (TextView) view.findViewById(R.id.updateNotesButtonID);

        loader = new Dialog(getActivity());
        loader.setContentView(R.layout.loader_alert);
        loader.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loader.setCancelable(false);

        titleText.setText(title);
        noteText.setText(note);

        updateNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loader.show();
                editNoteViewModel.getMessage(noteID, titleText.getText().toString().trim(), noteText.getText().toString().trim()).observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        loader.dismiss();
                        if(s.equals("updated")){

                            Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                                    R.anim.slide_in,  // enter
                                    R.anim.fade_out,  // exit
                                    R.anim.fade_in,   // popEnter
                                    R.anim.slide_out  // popExit
                            ).replace(R.id.frame_container, new View_note_fragment(noteID)).commit();
                        }else {
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        return view;
    }
}