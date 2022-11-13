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

    BottomNavigationView bottomnavi;
    WelcomeAdminScreen adminwelcome;
    ComplaintsFrag complaintsfrag;
    SettingsFragment settingsfrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_admin_screen);

        bottomnavi = findViewById(R.id.bottomnav);

        bottomnavi.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        setContentView(R.layout.activity_welcome_admin_screen);
                        return true;
                    case R.id.notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,complaintsfrag).commit();
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingsfrag).commit();
                        return true;

                }
                return false;
            }
        });

        MaterialButton logout = (MaterialButton) findViewById(R.id.logout);
        logout.setOnClickListener(v->
        {
            openMainActivity();
        });
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}