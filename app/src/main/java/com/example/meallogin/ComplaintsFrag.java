package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ComplaintsFrag extends AppCompatActivity {

    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaintsfrag);
        recyclerview = findViewById(R.id.recyclerView);
        setupRecyclerView();
    }

    void setupRecyclerView() {

    }
}