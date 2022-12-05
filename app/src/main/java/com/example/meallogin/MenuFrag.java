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

        MealAdapter mealAdapter = new MealAdapter(MenuFrag.this, cook.getMenu().getMeallist(), cook);
        binding.CookMeals.setAdapter(mealAdapter);
        binding.CookMeals.setClickable(true);
        binding.CookMeals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //The page that pops up when you click on an individual meal
                Intent intent = new Intent(MenuFrag.this, MealActivity.class);
                intent.putExtra("Name", cook.getMenu().menuNames().get(position));
                intent.putExtra("Price", cook.getMenu().getOffered().get(position).getPrice());
                intent.putExtra("Description", cook.getMenu().getOffered().get(position).getDescription());
                intent.putExtra("Cuisine Type", cook.getMenu().getOffered().get(position).getCuisineType());

                //Turn the Arraylists into Strings
                StringBuffer in = new StringBuffer();
                StringBuffer al = new StringBuffer();
                int n = 0;
                for (String s : cook.getMenu().getOffered().get(position).getIngredients()) {
                    in.append(s);
                    //Append the word
                    n++;
                    //if not the last word, add a comma and a space
                    if (n == cook.getMenu().getOffered().get(position).getIngredients().size() == false) {
                        in.append(", ");
                    }
                }
                n = 0;
                for (String s : cook.getMenu().getOffered().get(position).getAllergens()) {
                    al.append(s);
                    n++;
                    if (n == cook.getMenu().getOffered().get(position).getAllergens().size() == false) {
                        in.append(", ");
                    }
                }

                intent.putExtra("Ingredients", in.toString());
                intent.putExtra("Allergens", al.toString());
                intent.putExtra("Previous Class", EditMenu.class);
                intent.putExtra("Cook", cook);
                startActivity(intent);
            }
        });


        TextView mealName = (TextView) findViewById(R.id.MealName);
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

    void onViewStateRestored(Bundle bundle) {

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