package com.example.dailynotes.View.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailynotes.Adapter.Note_adapter;
import com.example.dailynotes.Model.Notes.Notes_response;
import com.example.dailynotes.Model.Profile.Profile_response;
import com.example.dailynotes.Model.Session_Management;
import com.example.dailynotes.R;
import com.example.dailynotes.ViewModel.Delete_note_ViewModel;
import com.example.dailynotes.ViewModel.Notes_ViewModel;
import com.example.dailynotes.ViewModel.ProfileViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home_fragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener, Note_adapter.OnItemClickListener, Note_adapter.OnItemDeleteClickListener {

    Session_Management session_management;
    String userID;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView userNameText;
    ProfileViewModel profileViewModel;
    Notes_ViewModel notesViewModel;
    Delete_note_ViewModel deleteNoteViewModel;
    String name;
    ActionBarDrawerToggle actionBarDrawerToggle;
    LinearLayout headerView;
    ExtendedFloatingActionButton addNotesButton;
    RecyclerView noteView;
    private Note_adapter noteAdapter;
    private List<Notes_response> noteList;
    Dialog loader;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        main();
        notes();
        addNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to_add_notes_fragment();
            }
        });
    }

    private void to_add_notes_fragment() {
        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        ).replace(R.id.frame_container, new Add_notes_fragment(userID)).addToBackStack(null).commit();
    }

    private void notes() {

        notesViewModel.getNotes(userID).observe(getViewLifecycleOwner(), new Observer<List<Notes_response>>() {
            @Override
            public void onChanged(List<Notes_response> notes_ID_respons) {
                noteList = new ArrayList<>();
                noteList = notes_ID_respons;
                noteAdapter = new Note_adapter(noteList);
                noteAdapter.setOnClickListener(Home_fragment.this::OnItemClick, Home_fragment.this::OnItemDelete);
                noteView.setAdapter(noteAdapter);
            }
        });
    }

    private void main() {
        profileViewModel.getMessage(userID).observe(getViewLifecycleOwner(), new Observer<Profile_response>() {
            @Override
            public void onChanged(Profile_response profile_response) {

                try {
                    name = profile_response.getName();

                } catch (Exception e) {
                    name = " ";
                }

                userNameText.setText(name);

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        notesViewModel = new ViewModelProvider(this).get(Notes_ViewModel.class);
        deleteNoteViewModel = new ViewModelProvider(this).get(Delete_note_ViewModel.class);
        session_management = new Session_Management(getActivity());
        userID = session_management.getSession();

        addNotesButton = (ExtendedFloatingActionButton) view.findViewById(R.id.addNotesButtonID);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        View header = navigationView.inflateHeaderView(R.layout.header);
        userNameText = header.findViewById(R.id.userNameTextID);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        //ActionBar

        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        noteView = (RecyclerView) view.findViewById(R.id.noteViewID);
        noteView.setHasFixedSize(true);
        noteView.setLayoutManager(new LinearLayoutManager(getActivity()));
        noteView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && addNotesButton.getVisibility() == View.VISIBLE) {
                    addNotesButton.hide();
                } else if (dy < 0 && addNotesButton.getVisibility() != View.VISIBLE) {
                    addNotesButton.show();
                }
            }
        });

        loader = new Dialog(getActivity());
        loader.setContentView(R.layout.loader_alert);
        loader.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loader.setCancelable(false);


        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.log_out) {
            session_management.removeSession();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Login_fragment()).commit();
        } else if (item.getItemId() == R.id.add_notes) {
            to_add_notes_fragment();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void OnItemClick(int position) {

        Notes_response response = noteList.get(position);

        String noteID = String.valueOf(response.getNoteID());

        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
                R.anim.slide_in,  // enter
                R.anim.fade_out,  // exit
                R.anim.fade_in,   // popEnter
                R.anim.slide_out  // popExit
        ).replace(R.id.frame_container, new View_note_fragment(noteID)).addToBackStack(null).commit();

    }

    @Override
    public void OnItemDelete(int position) {
        Notes_response response = noteList.get(position);

        String noteID = String.valueOf(response.getNoteID());

        Dialog deleteAlert = new Dialog(getActivity());
        deleteAlert.setContentView(R.layout.delete_alert);
        deleteAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        deleteAlert.setCancelable(false);
        deleteAlert.show();

        TextView yesButton = (TextView) deleteAlert.findViewById(R.id.yesButtonID);
        TextView noButton = (TextView) deleteAlert.findViewById(R.id.noButtonID);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAlert.dismiss();
                loader.show();
                deleteNoteViewModel.deleteNotes(noteID).observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        loader.dismiss();
                        if (s.equals("deleted")) {
                            Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                            notes();
                        } else {
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAlert.dismiss();
            }
        });

    }
}