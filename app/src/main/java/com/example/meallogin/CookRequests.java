package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.meallogin.databinding.ActivityComplaintsfragBinding;
import com.example.meallogin.databinding.ActivityCookSettingsBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

public class CookRequests extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref = db.getReference();
    ActivityCookSettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_cook_settings);
        Cook cook = (Cook)getIntent().getSerializableExtra("Cook");

        binding = ActivityCookSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DatabaseReference complaintsRef = db.getReference("Requests").orderByChild("status").equalTo(false).getRef();
        complaintsRef.get().addOnCompleteListener(task -> {
                    //Allows for binding to be displayed while reading from database
                    if (task.isSuccessful()) {
                        GenericTypeIndicator<ArrayList<Request>> t = new GenericTypeIndicator<ArrayList<Request>>() {
                        };
                        ArrayList<Request> complaints = new ArrayList<Request>();
                        int i = 0;
                        for (DataSnapshot ds : task.getResult().getChildren()) {
                            Request c = ds.getValue(Request.class);
                            i++;
                            if (!(c.isStatus() == true)) {
                                complaints.add(c);
                            }
                            //add outstanding complaints to list to be dealt with
                        }
                        RequestsAdapter requestsAdapter = new RequestsAdapter(CookRequests.this, complaints);
                        binding.complaintTable.setAdapter(requestsAdapter);




                    }
                });

        MaterialButton home = (MaterialButton) findViewById(R.id.CookHome);
        home.setOnClickListener(v01->{
            openCook(cook);
        });
        MaterialButton editMenuButton = (MaterialButton) findViewById(R.id.editMenuButton);
        if(cook.getStatus()){
            editMenuButton.setEnabled(false);
        }
        editMenuButton.setOnClickListener(v -> {
            openMenu(cook);
        });

        MaterialButton settings = (MaterialButton) findViewById(R.id.CookSettings);
        settings.setOnClickListener(v->{
            openSettings(cook);
        });
    }
    public void openSettings(Cook cook){
        Intent intent = new Intent(this, CookRequests.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }
    public void openMenu(Cook cook){
        Intent intent = new Intent(this, MenuFrag.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }
    public void openCook(Cook cook){
        Intent intent = new Intent(this, WelcomeCookScreen.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }
}