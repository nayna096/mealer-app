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

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    ActivitySearchMealsBinding binding;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Client client = (Client) getIntent().getSerializableExtra("Client");
        binding = ActivitySearchMealsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        search = findViewById(R.id.searchMeals);
        DatabaseReference complaintsRef = db.getReference("Cooks").child("Menu").child("offered");
        complaintsRef.get().addOnCompleteListener(task -> {
            //Allows for binding to be displayed while reading from database
            if(task.isSuccessful()){
                GenericTypeIndicator<ArrayList<Meal>> t = new GenericTypeIndicator<ArrayList<Meal>>(){};
                ArrayList<Meal> meals = new ArrayList<Meal>();
                for(DataSnapshot ds: task.getResult().getChildren()){
                    Meal c = ds.getValue(Meal.class);
                    meals.add(c);
                }
                MealSearchAdapter mealSearchAdapter = new MealSearchAdapter(SearchMeals.this, meals);
                binding.mealTable.setAdapter(mealSearchAdapter);
                //future code written here
            }

        });
        MaterialButton home = (MaterialButton) findViewById(R.id.Home);
        home.setOnClickListener(v -> {
            openWelcomeClientScreen(client);
        });
        MaterialButton search = (MaterialButton) findViewById(R.id.search);
        search.setOnClickListener(v -> {
            openSearchMeals(client);
        });
        MaterialButton settings = (MaterialButton) findViewById(R.id.settings);
        settings.setOnClickListener(v -> {
            openClientSettingsFrag(client);
        });
    }
    public void openWelcomeClientScreen(Client client) {
        Intent intent = new Intent(this, WelcomeClientScreen.class);
        intent.putExtra("Client", client);
        startActivity(intent);
    }
    public void openClientSettingsFrag(Client client) {
        Intent intent = new Intent(this, ClientSettingsFrag.class);
        intent.putExtra("Client", client);
        startActivity(intent);
    }
    public void openSearchMeals(Client client) {
        Intent intent = new Intent(this, SearchMeals.class);
        intent.putExtra("Client", client);
        startActivity(intent);
    }
    }

