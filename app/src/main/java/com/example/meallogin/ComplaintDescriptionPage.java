package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meallogin.databinding.ActivityComplaintDescriptionPageBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComplaintDescriptionPage extends AppCompatActivity {
    ActivityComplaintDescriptionPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComplaintDescriptionPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Meal meal =(Meal) getIntent().getSerializableExtra("Meal");
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");
        EditText message = (EditText) findViewById(R.id.ComplaintText);
        MaterialButton complaintSent = (MaterialButton) findViewById(R.id.sendComplaint);

        complaintSent.setOnClickListener(v->{
            String description = message.getText().toString();
            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Complaints");
            String id = dbref.push().getKey();
            dbref.child(id).setValue(new Complaint(description,cook,meal));
            Toast.makeText(getApplicationContext(),"Your complaint has been sent!",Toast.LENGTH_LONG).show();
            finish();
        });
    }
}