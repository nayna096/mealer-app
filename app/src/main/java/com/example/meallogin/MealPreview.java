package com.example.meallogin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.meallogin.databinding.ActivityMealBinding;
import com.example.meallogin.databinding.ActivityMealPreviewBinding;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public class MealPreview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_preview);

        Meal meal = (Meal) getIntent().getSerializableExtra("Meal");
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");
        Client client = (Client) getIntent().getSerializableExtra("Client");

        TextView name = (TextView) findViewById(R.id.Name);
        name.setText(meal.getName());

        TextView CookName = (TextView) findViewById(R.id.cookName);
        CookName.setText(cook.getUsername());

        TextView mealDescription = (TextView) findViewById(R.id.Description);
        mealDescription.setText(meal.getDescription());

        TextView mealPrice = (TextView) findViewById(R.id.Price);
        mealPrice.setText("Price: " + String.valueOf(meal.getPrice()));

        TextView mealCuisine = (TextView) findViewById(R.id.Cuisine);
        mealCuisine.setText("Type of food: " + meal.getCuisineType());

        TextView mealIngredients = (TextView) findViewById(R.id.Ingredients);
        mealIngredients.setText("Ingredients: " + meal.getIngredients().toString());

        TextView mealAllergens = (TextView) findViewById(R.id.Allergens);
        mealAllergens.setText("Allergens: " + meal.getAllergens().toString());

        MaterialButton back = (MaterialButton) findViewById(R.id.BackButton);
        back.setOnClickListener(v -> {
            finish();
        });
        MaterialButton request = (MaterialButton) findViewById(R.id.requestMeal);
        request.setOnClickListener(v -> {
            Intent i = new Intent(MealPreview.this, RequestDescriptionPage.class);
            i.putExtra("Meal", meal);
            i.putExtra("Cook", cook);
            i.putExtra("Client", client);
            i.putExtra("Origin", MealPreview.class);
            //go to request description screen
            startActivity(i);
        });

    }

}