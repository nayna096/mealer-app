package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CreateMeal extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref= db.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");

        MaterialButton done = (MaterialButton) findViewById(R.id.doneButton);
        done.setOnClickListener(v -> {
            String name = ((EditText) findViewById(R.id.newMealName)).getText().toString();
            String cuisineType = ((EditText) findViewById(R.id.newMealCuisineType)).getText().toString();
            String ingredientsStr = ((EditText) findViewById(R.id.newMealIngredients)).getText().toString();
            String allergensStr = ((EditText) findViewById(R.id.newMealAllergens)).getText().toString();
            String priceStr = ((EditText) findViewById(R.id.newMealPrice)).getText().toString();
            String description = ((EditText) findViewById(R.id.newMealDescription)).getText().toString();

            List<String> ingredientsUnsorted = new ArrayList<>(Arrays.asList(ingredientsStr.split(",")));
            List<String> ingredients = ingredientsUnsorted.stream().sorted().collect(Collectors.toList());
            List<String> allergensUnsorted = new ArrayList<>(Arrays.asList(allergensStr.split(",")));
            List<String> allergens = allergensUnsorted.stream().sorted().collect(Collectors.toList());
            double price = Double.parseDouble(priceStr);

            cook.addToMealList(new Meal(name, cuisineType, ingredients, allergens, price, description));

            Toast.makeText(getApplicationContext(), "Meal created successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, WelcomeCookScreen.class);
            startActivity(intent);
        });


    }
}