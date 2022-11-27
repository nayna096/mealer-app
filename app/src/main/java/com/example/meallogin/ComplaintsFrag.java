package com.example.meallogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.meallogin.R.id;
import com.example.meallogin.databinding.ActivityComplaintsfragBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Iterator;

public class ComplaintsFrag extends AppCompatActivity {

    RecyclerView recyclerview;
    ComplaintAdapter complaintAdapter;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref = db.getReference();
    ActivityComplaintsfragBinding binding;
    Administrator admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComplaintsfragBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        admin = (Administrator)getIntent().getSerializableExtra("Admin");
//        recyclerview = findViewById(R.id.recyclerView);
//        setupRecyclerView();

        //find an arraylist of type complaints of all outstanding complaints
//        complaintGenerator();
        ArrayList<Complaint> complaints = new ArrayList<Complaint>();

        //Need the complaints to be in an arraylist that is able to be modified
        //in the thread.
//        complaints.add(new Complaint("Not Good", new Cook("Noah","TA","noahta1","et")));
        ComplaintAdapter complaintAdapter = new ComplaintAdapter(ComplaintsFrag.this, complaints);
        binding.complaintTable.setAdapter(complaintAdapter);

        MaterialButton home = (MaterialButton) findViewById(R.id.Home);
        home.setOnClickListener(v -> {
            openWelcomeAdminScreen();
        });
//        MaterialButton complaints = (MaterialButton) findViewById(R.id.complaints);
//        complaints.setOnClickListener(v -> {
//            openComplaintsFrag();
//        });
        MaterialButton settings = (MaterialButton) findViewById(R.id.settings);
        settings.setOnClickListener(v -> {
            openSettingsFrag();
        });
    }
//    ArrayList<Complaint> complaintGenerator(){
//        ArrayList<Complaint> complaints = new ArrayList<Complaint>();
//        dbref.child("Complaints").orderByChild("suspensionDate").equalTo("unknown").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if(snapshot.exists()){
//                    for(DataSnapshot ds: snapshot.getChildren()){
//                        Complaint complaint = ds.getValue(Complaint.class);
//                        Administrator admin = new Administrator();
//                        admin.addComplaint(complaint);
//                    }
//                }else{
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return complaints;
//    }
//    void setupRecyclerView() {
//        Query query = FirebaseFirestore.getInstance().collection("Complaints").orderBy("username");
//        FirestoreRecyclerOptions<Complaint> options = new FirestoreRecyclerOptions.Builder<Complaint>()
//                .setQuery(query,Complaint.class).build();
//        recyclerview.setLayoutManager(new LinearLayoutManager(this));
////        complaintAdapter = new ComplaintAdapter(options, this);
////        recyclerview.setAdapter((complaintAdapter));
//    }

//    @Override
//    protected void onStart(){
//        super.onStart();
////        complaintAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop(){
//        super.onStop();
////        complaintAdapter.stopListening();
//    }
//
//    @Override
//    protected void onResume(){
//        super.onResume();
////        complaintAdapter.notifyDataSetChanged();
//    }
//    public void openComplaintsFrag() {
//        Intent intent = new Intent(this, ComplaintsFrag.class);
//        startActivity(intent);
//    }
    public void openWelcomeAdminScreen() {
        Intent intent = new Intent(this, WelcomeAdminScreen.class);
        startActivity(intent);
    }
    public void openSettingsFrag() {
        Intent intent = new Intent(this, SettingsFrag.class);
        startActivity(intent);
    }
}