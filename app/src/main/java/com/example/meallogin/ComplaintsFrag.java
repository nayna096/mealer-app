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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComplaintsfragBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DatabaseReference complaintsRef = db.getReference().child("Complaints");
        complaintsRef.get().addOnCompleteListener(task -> {
            //Allows for binding to be displayed while reading from database
            if(task.isSuccessful()){
                GenericTypeIndicator<ArrayList<Complaint>> t = new GenericTypeIndicator<ArrayList<Complaint>>(){};
                ArrayList<Complaint> complaints = new ArrayList<Complaint>();
                for(DataSnapshot ds: task.getResult().getChildren()){
                    Complaint c = ds.getValue(Complaint.class);
                    complaints.add(c);
                    //add outstanding complaints to list to be dealt with
                }
                ComplaintAdapter complaintAdapter = new ComplaintAdapter(ComplaintsFrag.this, complaints);
                binding.complaintTable.setAdapter(complaintAdapter);
                //future code written here
            }

        });


        //find an arraylist of type complaints of all outstanding complaints
//        complaintGenerator();

        //Need the complaints to be in an arraylist that is able to be modified
        //in the thread.
//        complaints.add(new Complaint("Not Good", new Cook("Noah","TA","noahta1","et")));


        MaterialButton home = (MaterialButton) findViewById(R.id.Home);
        home.setOnClickListener(v -> {
            openWelcomeAdminScreen();
        });
        MaterialButton settings = (MaterialButton) findViewById(R.id.settings);
        settings.setOnClickListener(v -> {
            openSettingsFrag();
        });
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