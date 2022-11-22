package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.meallogin.databinding.ActivityEditMenuBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EditMenu extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref= db.getReference();
    ActivityEditMenuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");
        ListAdapter listAdapter = new ListAdapter(EditMenu.this,cook.getMenu().getOffered());
        binding.menuTable.setAdapter(listAdapter);
//        binding.menuTable.setClickable(true);
        MaterialButton addMealButton = (MaterialButton) findViewById(R.id.menuAddMealButton);
        MaterialButton removeMealButton = (MaterialButton) findViewById(R.id.menuRemoveMealButton);


        addMealButton.setOnClickListener(v1 -> {
            Intent intent = new Intent(this, AddMeal.class);
            intent.putExtra("Cook", cook);
            startActivity(intent);
        });

        removeMealButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RemoveMeal.class);
            intent.putExtra("Cook", cook);
            startActivity(intent);
        });
    }
}