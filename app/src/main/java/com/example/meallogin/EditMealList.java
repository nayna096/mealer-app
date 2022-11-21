package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.meallogin.databinding.ActivityEditMealListBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EditMealList extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref= db.getReference();
    ActivityEditMealListBinding binding;
//    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = EditMealList
        binding = ActivityEditMealListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");
        ListAdapter listAdapter = new ListAdapter(EditMealList.this,cook.getMenu().getMeallist());

        //region populate MealList LinearLayout
//        ListView mealListTable = (ListView) findViewById(R.id.mealListTable);
//
//        List<String> mealListNames = cook.getMenu().mealListNames();
//        TextView textViewML = new TextView(this);
//
//        for (int i=0; i<cook.getMenu().mealListSize(); i++) {
//            textViewML.setText(mealListNames.get(i));
//            mealListTable.addView(textViewML);
//        }
        //endregion

        MaterialButton createMealButton = (MaterialButton) findViewById(R.id.mealListCreateNewMealButton);
        MaterialButton deleteMealButton = (MaterialButton) findViewById(R.id.mealListDeleteMealButton);
        binding.mealListTable.setAdapter(listAdapter);
        binding.mealListTable.setClickable(true);
        createMealButton.setOnClickListener(v1 -> {
            Intent intent = new Intent(this, CreateMeal.class);
            intent.putExtra("Cook", cook);
            startActivity(intent);
        });

        deleteMealButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, DeleteMeal.class);
            intent.putExtra("Cook", cook);
            startActivity(intent);
        });
    }

    public static boolean isSuspended(Cook cook) {
        return cook.getStatus();
    }
}