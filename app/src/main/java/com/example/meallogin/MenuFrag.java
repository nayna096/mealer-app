package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.meallogin.databinding.ActivityMenuFragBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuFrag extends AppCompatActivity {
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();

    ActivityMenuFragBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");
        binding = ActivityMenuFragBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MealAdapter mealAdapter = new MealAdapter(MenuFrag.this, cook.getMenu().getMeals(), cook);
        binding.CookMeals.setAdapter(mealAdapter);

        MaterialButton createMeal = (MaterialButton) findViewById(R.id.CreateMeal);
        createMeal.setOnClickListener(v -> {
            CreateMeal(cook);
        });

        MaterialButton home = (MaterialButton) findViewById(R.id.CookHome);
        home.setOnClickListener(v01 -> {
            openCook(cook);
        });
        MaterialButton editMenuButton = (MaterialButton) findViewById(R.id.editMenuButton);
        editMenuButton.setOnClickListener(v -> {
            openMenu(cook);
        });

        MaterialButton settings = (MaterialButton) findViewById(R.id.CookSettings);
        settings.setOnClickListener(v -> {
            openSettings(cook);
        });

    }

    public void openSettings(Cook cook) {
        Intent intent = new Intent(this, CookSettingsFrag.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }

    public void openMenu(Cook cook) {
        Intent intent = new Intent(this, MenuFrag.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }

    public void openCook(Cook cook) {
        Intent intent = new Intent(this, WelcomeCookScreen.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }

    public void CreateMeal(Cook cook) {
        Intent intent = new Intent(this, CreateMeal.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }
}