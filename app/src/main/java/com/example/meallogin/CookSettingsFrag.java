package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class CookSettingsFrag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Cook cook = (Cook)getIntent().getSerializableExtra("Cook");


        setContentView(R.layout.activity_cook_settings);
        MaterialButton home = (MaterialButton) findViewById(R.id.CookHome);
        home.setOnClickListener(v01->{
            openCook(cook);
        });
        MaterialButton editMenuButton = (MaterialButton) findViewById(R.id.editMenuButton);
        editMenuButton.setOnClickListener(v -> {
            openMenu(cook);
        });

        MaterialButton settings = (MaterialButton) findViewById(R.id.CookSettings);
        settings.setOnClickListener(v->{
            openSettings(cook);
        });
    }
    public void openSettings(Cook cook){
        Intent intent = new Intent(this, CookSettingsFrag.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }
    public void openMenu(Cook cook){
        Intent intent = new Intent(this, MenuFrag.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }
    public void openCook(Cook cook){
        Intent intent = new Intent(this, WelcomeCookScreen.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }
}