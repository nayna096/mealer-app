package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meallogin.databinding.ActivityRequestDescriptionPageBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestDescriptionPage extends AppCompatActivity {

    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Requests");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_description_page);

        Meal meal = (Meal) getIntent().getSerializableExtra("Meal");
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");
        Client client = (Client) getIntent().getSerializableExtra("Client");
        Class activity = (Class) getIntent().getSerializableExtra("Origin");

        EditText description = (EditText) findViewById(R.id.requestDescription);
        MaterialButton order = (MaterialButton) findViewById(R.id.orderButton);
        order.setOnClickListener(v -> {
            Request request = new Request(meal, cook, client, description.getText().toString());
            String id = dbref.push().getKey();
            dbref.child(id).setValue(request);
            Toast.makeText(getApplicationContext(),"Successfully put in the request!",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,SearchMeals.class);
            intent.putExtra("Client",client);
            startActivity(intent);
        });
        MaterialButton back = (MaterialButton) findViewById(R.id.backButton);
        back.setOnClickListener(v->{
            Intent intent = new Intent(this,activity.getClass());
            intent.putExtra("Meal",meal);
            intent.putExtra("Cook",cook);
            intent.putExtra("Client",client);
            startActivity(intent);
        });
    }
}