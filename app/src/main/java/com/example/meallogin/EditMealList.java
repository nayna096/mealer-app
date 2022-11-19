package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EditMealList extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref= db.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal_list);
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");


        //region populate MealList LinearLayout
        LinearLayout mealListTable = (LinearLayout) findViewById(R.id.mealListTable);

        List<String> mealListNames = cook.getMenu().mealListNames();
        TextView textViewML = new TextView(this);

        for (int i=0; i<cook.getMenu().mealListSize(); i++) {
            textViewML.setText(mealListNames.get(i));
            mealListTable.addView(textViewML);
        }
        //endregion

        MaterialButton createMealButton = (MaterialButton) findViewById(R.id.mealListCreateNewMealButton);
        MaterialButton deleteMealButton = (MaterialButton) findViewById(R.id.mealListDeleteMealButton);

        createMealButton.setOnClickListener(v1 -> {
            Intent intent = new Intent(this, CreateMeal.class);
            //intent.putExtra("Cook", cook);
            startActivity(intent);
        });

        deleteMealButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DeleteMeal.class);
            intent.putExtra("Cook", cook);
            startActivity(intent);
        });
    }
}