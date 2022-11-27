package com.example.meallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;

public class WelcomeAdminScreen extends AppCompatActivity {

    Administrator admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_admin_screen);

        //retrieve Admin with list of outstanding complaints
        admin = (Administrator) getIntent().getSerializableExtra("Admin");
        MaterialButton home = (MaterialButton) findViewById(R.id.Home);
        home.setOnClickListener(v -> {
            openWelcomeAdminScreen();
        });
        MaterialButton complaints = (MaterialButton) findViewById(R.id.complaints);
        complaints.setOnClickListener(v -> {
            openComplaintsFrag();
        });
        MaterialButton settings = (MaterialButton) findViewById(R.id.settings);
        settings.setOnClickListener(v -> {
            openSettingsFrag();
        });


        MaterialButton logout = (MaterialButton) findViewById(R.id.logout);
        logout.setOnClickListener(v ->
        {
            openMainActivity();
        });
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openComplaintsFrag() {
        Intent intent = new Intent(this, ComplaintsFrag.class);
        intent.putExtra("Admin", admin);
        startActivity(intent);
    }

    public void openWelcomeAdminScreen() {
        Intent intent = new Intent(this, WelcomeAdminScreen.class);
        startActivity(intent);
    }

    public void openSettingsFrag() {
        Intent intent = new Intent(this, SettingsFrag.class);
        startActivity(intent);
    }
}