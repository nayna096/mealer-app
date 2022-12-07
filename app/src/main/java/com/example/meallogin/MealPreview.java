package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.meallogin.databinding.ActivityMealBinding;
import com.example.meallogin.databinding.ActivityMealPreviewBinding;
import com.google.android.material.button.MaterialButton;

public class MealPreview extends AppCompatActivity {

    ActivityMealPreviewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMealPreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Meal meal = (Meal)getIntent().getSerializableExtra("Meal");
        Cook cook = (Cook)getIntent().getSerializableExtra("Cook");
        Client client = (Client)getIntent().getSerializableExtra("Client");

        binding.Name.setText(meal.getName());
        binding.cookName.setText(cook.getUsername());
        binding.Description.setText(meal.getDescription());
        binding.Price.setText("Price: "+String.valueOf(meal.getPrice()));
        binding.Cuisine.setText("Type of food: "+meal.getCuisineType());
        binding.Ingredients.setText("Ingredients: "+meal.getIngredients().toString());
        binding.Allergens.setText("Allergens: "+meal.getAllergens().toString());

        MaterialButton back = (MaterialButton) findViewById(R.id.BackButton);
        back.setOnClickListener(v->{
            Intent i = new Intent(MealPreview.this,SearchMeals.class);
            i.putExtra("Meal",meal);
            i.putExtra("Cook",cook);
            i.putExtra("Client",client);
            //return to the specific screen from which we left
            startActivity(i);
        });
        MaterialButton request = (MaterialButton)findViewById(R.id.requestMeal);
        request.setOnClickListener(v->{
            Intent i = new Intent(MealPreview.this,RequestDescriptionPage.class);
            i.putExtra("Meal",meal);
            i.putExtra("Cook",cook);
            i.putExtra("Client",client);
            i.putExtra("Origin",MealPreview.class);
            //go to request description screen
            startActivity(i);
        });

    }
}