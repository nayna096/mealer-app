package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteMeal extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref= db.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_meal);
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");

        MaterialButton delete = (MaterialButton) findViewById(R.id.permanentlyDeleteButton);
        delete.setOnClickListener(v -> {
            String mealName = ((EditText) findViewById(R.id.toDeleteTextInput)).getText().toString();
            boolean exists = false;
            int i=0;
            while (i<cook.getMenu().MealListSize() && exists == false) {
                if (cook.getMenu().MealListNames().get(i).equals(mealName)) {
                    exists = true;
                }
                i++;
            }
            if (exists) {
                cook.getMenu().deletefromMeallist(cook.getMenu().findMealByName(mealName));
            } else {
                Toast.makeText(getApplicationContext(), "Failed. Please try again.", Toast.LENGTH_LONG).show();
            }

            Toast.makeText(getApplicationContext(), "Meal deleted successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, WelcomeCookScreen.class);
            startActivity(intent);
        });
    }
}