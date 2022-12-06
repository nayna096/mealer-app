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
        Client client = (Client) getIntent().getSerializableExtra("Client");
        binding = ActivitySearchMealsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        search = findViewById(R.id.searchMeals);
        DatabaseReference cooksRef = db.getReference("Cooks").orderByChild("status").equalTo(false).getRef();
        cooksRef.get().addOnCompleteListener(task -> {
            //Allows for binding to be displayed while reading from database
            if (task.isSuccessful()) {
                GenericTypeIndicator<ArrayList<Meal>> t = new GenericTypeIndicator<ArrayList<Meal>>() {
                };
                ArrayList<Meal> meals = new ArrayList<Meal>();
                ArrayList<String> cooks = new ArrayList<>();
                ArrayList<Menu> menus = new ArrayList<>();
                for (DataSnapshot ds : task.getResult().getChildren()) {
                    Cook cook = ds.getValue(Cook.class);
                    cooks.add(cook.getUsername());
                    menus.add(cook.getMenu());
                    for (DataSnapshot dsi : ds.child("menu/meals").getChildren()) {
                        Meal c = dsi.getValue(Meal.class);
                        if (c.isOffered()) {
                            meals.add(c);
                        }

                    }

                }

                MaterialButton go = findViewById(R.id.enterSearch);
                go.setOnClickListener(v -> {
                    ArrayList<Meal> meals2 = new ArrayList<Meal>();
                    for (int i = 0; i < meals.size(); i++) {
//                        System.out.println(i);
                        //the search filters

                        //searching by meal name
                        if (meals.get(i).getName().toLowerCase(Locale.ROOT).contains(search.getText().toString().toLowerCase(Locale.ROOT))) {
                            meals2.add(meals.get(i));
                        }
                        //searching by cuisine type
                        if(meals.get(i).getCuisineType().toLowerCase(Locale.ROOT).contains(search.getText().toString().toLowerCase(Locale.ROOT))){
                            meals2.add(meals.get(i));
                        }

                        //searching by cook name
                        if(cooks.contains(search.getText().toString())){
                            for(int j = 0;j<cooks.size();j++){
                                if(cooks.get(j).equals(search.getText().toString())){
                                    for(int k = 0;k< menus.get(j).getMeals().size();k++){
                                        if(menus.get(j).getMeals().get(k).isOffered()){
                                            meals2.add(menus.get(j).getMeals().get(k));
                                        }
                                    }
                                }
                            }
                            break;
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

