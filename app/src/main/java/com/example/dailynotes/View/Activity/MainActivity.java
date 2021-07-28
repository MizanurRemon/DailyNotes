package com.example.dailynotes.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dailynotes.R;
import com.example.dailynotes.View.Fragment.Login_fragment;
import com.example.dailynotes.View.Fragment.Registration_fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Login_fragment()).commit();
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        final Configuration override = new Configuration(newBase.getResources().getConfiguration());
        override.fontScale = 1f;
        applyOverrideConfiguration(override);
    }

    @Override
    public void onBackPressed() {

            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                //Toast.makeText(this, "Going Back", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().popBackStack();
                //getFragmentManager().popBackStack();

            } else {
                Dialog alert = new Dialog(MainActivity.this);
                alert.setContentView(R.layout.exit_alert);
                alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alert.setCancelable(false);
                alert.show();

                TextView yesButton = (TextView) alert.findViewById(R.id.yesButtonID);
                TextView noButton = (TextView) alert.findViewById(R.id.noButtonID);

                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                });

                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.cancel();
                    }
                });
        }
    }
}