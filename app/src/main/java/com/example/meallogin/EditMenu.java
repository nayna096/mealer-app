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

public class EditMenu extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref= db.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");

        //region populate Menu LinearLayout
        LinearLayout menuTable = (LinearLayout) findViewById(R.id.menuTable);
        List<String> menuNames = cook.getMenu().menuNames();
        TextView textViewMN = new TextView(this);

        for (int i=0; i<cook.getMenu().menuSize(); i++) {
            textViewMN.setText(menuNames.get(i));
            menuTable.addView(textViewMN);
        }
        //endregion

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