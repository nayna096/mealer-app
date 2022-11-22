package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.meallogin.databinding.ActivityMealBinding;
import com.google.android.material.button.MaterialButton;

import java.util.EventListener;

public class MealActivity extends AppCompatActivity {
    ActivityMealBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = this.getIntent();

        //retrieve passed data about the meal
        String name = (String)intent.getSerializableExtra("Name");
        binding.Name.setText(name);

        String description = (String)intent.getSerializableExtra("Description");
        binding.Description.setText(description);

        Double price = (Double)intent.getSerializableExtra("Price");
        binding.Price.setText("Price: "+String.valueOf(price));

        String cuisineType = (String)intent.getSerializableExtra("Cuisine Type");
        binding.Cuisine.setText("Type of food: "+cuisineType);

        CharSequence Ingredients = (CharSequence) intent.getSerializableExtra("Ingredients");
        binding.Ingredients.setText("Ingredients: "+Ingredients.toString());

        CharSequence Allergens = (CharSequence) intent.getSerializableExtra("Allergens");
        binding.Allergens.setText("Allergens: "+Allergens.toString());

        //retrieve data that is used to return to the previous screen
        Class origin = (Class)intent.getSerializableExtra("Previous Class");
        Cook cook = (Cook)intent.getSerializableExtra("Cook");


        Button back = (Button) findViewById(R.id.BackButton);
        back.setOnClickListener(v->
        {
            Intent i = new Intent(MealActivity.this,origin);
            i.putExtra("Cook",cook);
            //return to the specific screen from which we left
            startActivity(i);
        });
    }
}