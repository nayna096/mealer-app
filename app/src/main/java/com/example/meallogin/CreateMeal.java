package com.example.meallogin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CreateMeal extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref = db.getReference();

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");

        MaterialButton done = (MaterialButton) findViewById(R.id.doneButton);
        MaterialButton back = (MaterialButton)findViewById(R.id.createBackButton);
        done.setOnClickListener(v -> {
            //region Reading user input
            String name = ((EditText) findViewById(R.id.newMealName)).getText().toString();
            String cuisineType = ((EditText) findViewById(R.id.newMealCuisineType)).getText().toString();
            String ingredientsStr = ((EditText) findViewById(R.id.newMealIngredients)).getText().toString();
            String allergensStr = ((EditText) findViewById(R.id.newMealAllergens)).getText().toString();
            String priceStr = ((EditText) findViewById(R.id.newMealPrice)).getText().toString();
            String description = ((EditText) findViewById(R.id.newMealDescription)).getText().toString();
            //endregion

            //region Convert user input to required data types
            List<String> ingredientsUnsorted = new ArrayList<>(Arrays.asList(ingredientsStr.split(",")));
            List<String> ingredients = ingredientsUnsorted.stream().sorted().collect(Collectors.toList());
            List<String> allergensUnsorted = new ArrayList<>(Arrays.asList(allergensStr.split(",")));
            List<String> allergens = allergensUnsorted.stream().sorted().collect(Collectors.toList());
            double price = Double.parseDouble(priceStr);
            //endregion

            Meal meal = new Meal(name, cuisineType, ingredients, allergens, price, description);
            if(cook.getMenu().getMeallist().contains(meal)==false){
                //Check that the meal doesn't already exist in the cook's meal list
                cook.getMenu().addtoMeallist(meal);
                dbref.child("Cooks").orderByChild("username").equalTo(cook.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for(DataSnapshot d:snapshot.getChildren()){
                                Cook c = d.getValue(Cook.class);
                                if(c.getUsername().equals(cook.getUsername())){
                                    //Find the cook in the db to update their menu
                                    String id = d.getKey(); //Cook-unique id
                                    dbref.child("Cooks").child(id).child("menu").setValue(cook.getMenu());

                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });
                Intent intent = new Intent(CreateMeal.this, MenuFrag.class);
                intent.putExtra("Cook", cook);
                startActivity(intent);
            }else{
                //This meal already exists
                Toast.makeText(getApplicationContext(),"This meal already exists in your Meal List!", Toast.LENGTH_LONG).show();
            }
        });
        back.setOnClickListener(v1->{
            Intent intent = new Intent(this, MenuFrag.class);
            intent.putExtra("Cook", cook);
            startActivity(intent);
        });

    }
}