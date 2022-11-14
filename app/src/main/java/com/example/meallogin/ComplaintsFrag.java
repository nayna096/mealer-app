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
import com.google.android.material.navigation.NavigationBarView;

public class ComplaintsFrag extends AppCompatActivity {

    RecyclerView recyclerview;
    ComplaintAdapter complaintAdapter;
    BottomNavigationView bottomNavigationView = findViewById(id.bottomnav);
    SettingsFragment settingsfrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaintsfrag);
        recyclerview = findViewById(R.id.recyclerView);
        setupRecyclerView();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        //setContentView(R.layout.activity_welcome_admin_screen);
                        openWelcomeAdminScreen();
                        return true;
                    case R.id.notification:
                        //setContentView(R.layout.activity_complaintsfrag);
                        openComplaintsFrag();
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingsfrag).commit();
                        return true;

                }
                return false;
            }
        });
    }

    void setupRecyclerView() {
        FirestoreRecyclerOptions<Complaint> options = new FirestoreRecyclerOptions.Builder<Complaint>().build();
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
}