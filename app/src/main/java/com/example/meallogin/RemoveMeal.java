package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RemoveMeal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_meal);

        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");

        MaterialButton remove = (MaterialButton) findViewById(R.id.removeButton);
        remove.setOnClickListener(v -> {
            String mealName = ((EditText) findViewById(R.id.toRemoveTextInput)).getText().toString();
//            boolean exists = false;
//            int i = 0;
//            while (i < cook.getMenu().mealListSize() && exists == false) {
//                if (cook.getMenu().mealListNames().get(i).equals(mealName)) {
//                    exists = true;
//                }
//                i++;
//            }
            if (cook.getMenu().getOffered().contains(cook.getMenu().findMealByName(mealName))) {
                cook.getMenu().removefromOffered(cook.getMenu().findMealByName(mealName));
            } else {
                Toast.makeText(getApplicationContext(), "Failed. Please try again.", Toast.LENGTH_LONG).show();
            }

            Toast.makeText(getApplicationContext(), "Meal removed successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, WelcomeCookScreen.class);
            startActivity(intent);
        });

    }
}