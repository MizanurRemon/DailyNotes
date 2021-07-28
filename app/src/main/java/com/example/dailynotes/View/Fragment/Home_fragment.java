package com.example.dailynotes.View.Fragment;

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

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailynotes.Model.Profile.Profile_response;
import com.example.dailynotes.Model.Session_Management;
import com.example.dailynotes.R;
import com.example.dailynotes.ViewModel.LoginViewModel;
import com.example.dailynotes.ViewModel.ProfileViewModel;
import com.google.android.material.navigation.NavigationView;

public class Home_fragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    Session_Management session_management;
    String userID;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView userNameText;
    ProfileViewModel profileViewModel;
    String name;
    ActionBarDrawerToggle actionBarDrawerToggle;
    LinearLayout headerView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        main();
    }

    private void main() {
        profileViewModel.getMessage(userID).observe(getViewLifecycleOwner(), new Observer<Profile_response>() {
            @Override
            public void onChanged(Profile_response profile_response) {

                try {
                    name = profile_response.getName();

                }catch (Exception e){
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
        session_management = new Session_Management(getActivity());
        userID = session_management.getSession();



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
        if(item.getItemId() == R.id.log_out){
            session_management.removeSession();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Login_fragment()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}