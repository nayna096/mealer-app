package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.meallogin.databinding.ActivityComplaintsfragBinding;
import com.example.meallogin.databinding.ActivitySearchMealsBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

public class SearchMeals extends AppCompatActivity {

    ComplaintAdapter complaintAdapter;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref = db.getReference();
    ActivitySearchMealsBinding binding;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchMealsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        search = findViewById(R.id.searchMeals);
        DatabaseReference complaintsRef = db.getReference().child("Cooks").child("Menu").child("offered");
        complaintsRef.get().addOnCompleteListener(task -> {
            //Allows for binding to be displayed while reading from database
            if(task.isSuccessful()){
                GenericTypeIndicator<ArrayList<Meal>> t = new GenericTypeIndicator<ArrayList<Meal>>(){};
                ArrayList<Meal> meals = new ArrayList<Meal>();
                for(DataSnapshot ds: task.getResult().getChildren()){
                    Meal c = ds.getValue(Meal.class);
                    meals.add(c);
                    //add outstanding complaints to list to be dealt with
                }
                MealSearchAdapter mealSearchAdapter = new MealSearchAdapter(SearchMeals.this, meals);
                binding.mealTable.setAdapter(mealSearchAdapter);
                //future code written here
            }

        });
        MaterialButton home = (MaterialButton) findViewById(R.id.Home);
        home.setOnClickListener(v -> {
            openWelcomeAdminScreen();
        });
        MaterialButton settings = (MaterialButton) findViewById(R.id.settings);
        settings.setOnClickListener(v -> {
            openSettingsFrag();
        });
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

