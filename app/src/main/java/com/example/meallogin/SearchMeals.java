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
import java.util.Locale;

public class SearchMeals extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    ActivitySearchMealsBinding binding;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Client client = (Client) getIntent().getSerializableExtra("Clients");
        binding = ActivitySearchMealsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        search = findViewById(R.id.searchMeals);
        DatabaseReference cooksRef = db.getReference("Cooks").orderByChild("status").equalTo(false).getRef();
        cooksRef.get().addOnCompleteListener(task -> {
            //Allows for binding to be displayed while reading from database
            if(task.isSuccessful()){
                GenericTypeIndicator<ArrayList<Meal>> t = new GenericTypeIndicator<ArrayList<Meal>>(){};
                ArrayList<Meal> meals = new ArrayList<Meal>();
                for(DataSnapshot ds: task.getResult().getChildren()){
                    for (DataSnapshot dsi: ds.child("menu/offered").getChildren()){
                        if (dsi.getValue().equals(null)){
                            break;
                        }else{
                            Meal c = dsi.getValue(Meal.class);
                            meals.add(c);
                        }

                    }

                }

                MaterialButton go = findViewById(R.id.enterSearch);
                go.setOnClickListener(v -> {
                    ArrayList<Meal> meals2 = new ArrayList<Meal>();
                    for (int i = meals.size(); i > 1; i--){
                        if (meals.get(i).getName().toLowerCase(Locale.ROOT).contains(search.getText().toString().toLowerCase(Locale.ROOT))){
                            meals2.add(meals.get(i));
                        }
                    }
                    MealSearchAdapter mealSearchAdapter = new MealSearchAdapter(SearchMeals.this, meals2);
                    binding.mealTable.setAdapter(mealSearchAdapter);
                });
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

