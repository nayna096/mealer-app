package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.meallogin.R.id;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ComplaintsFrag extends AppCompatActivity {

    RecyclerView recyclerview;
    ComplaintAdapter complaintAdapter;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref = db.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaintsfrag);
        recyclerview = findViewById(R.id.recyclerView);
        setupRecyclerView();

        MaterialButton home = (MaterialButton) findViewById(R.id.Home);
        home.setOnClickListener(v -> {
            openWelcomeAdminScreen();
        });
        MaterialButton complaints = (MaterialButton) findViewById(R.id.complaints);
        complaints.setOnClickListener(v -> {
            openComplaintsFrag();
        });
        MaterialButton settings = (MaterialButton) findViewById(R.id.settings);
        settings.setOnClickListener(v -> {
            openSettingsFrag();
        });
    }

    void setupRecyclerView() {
        Query query = FirebaseFirestore.getInstance().collection("Complaints").orderBy("username");
        FirestoreRecyclerOptions<Complaint> options = new FirestoreRecyclerOptions.Builder<Complaint>()
                .setQuery(query,Complaint.class).build();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        complaintAdapter = new ComplaintAdapter(options, this);
        recyclerview.setAdapter((complaintAdapter));
    }

    @Override
    protected void onStart(){
        super.onStart();
        complaintAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        complaintAdapter.stopListening();
    }

    @Override
    protected void onResume(){
        super.onResume();
        complaintAdapter.notifyDataSetChanged();
    }
    public void openComplaintsFrag() {
        Intent intent = new Intent(this, ComplaintsFrag.class);
        startActivity(intent);
    }
    public void openWelcomeAdminScreen() {
        Intent intent = new Intent(this, WelcomeAdminScreen.class);
        startActivity(intent);
    }
    public void openSettingsFrag() {
        Intent intent = new Intent(this, SettingsFrag.class);
        startActivity(intent);
    }
}