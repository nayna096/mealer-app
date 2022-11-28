package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class ClientSettingsFrag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_settings_frag);
        MaterialButton home = (MaterialButton) findViewById(R.id.Home);
        home.setOnClickListener(v -> {
            openWelcomeClient();
        });
        MaterialButton search = (MaterialButton) findViewById(R.id.search);
        search.setOnClickListener(v -> {
            openSearchMeals();
        });
        MaterialButton settings = (MaterialButton) findViewById(R.id.settings);
        settings.setOnClickListener(v -> {
            openClientSettingsFrag();
        });
    }
    public void openWelcomeClient() {
        Intent intent = new Intent(this, WelcomeClientScreen.class);
        startActivity(intent);
    }
    public void openClientSettingsFrag() {
        Intent intent = new Intent(this, ClientSettingsFrag.class);
        startActivity(intent);
    }
    public void openSearchMeals() {
        Intent intent = new Intent(this, SearchMeals.class);
        startActivity(intent);
    }
}
