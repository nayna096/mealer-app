package com.example.meallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PasswordReset extends AppCompatActivity {
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        TextView password = (TextView) findViewById(R.id.requestDescription);
        GeneralUser user = (GeneralUser) getIntent().getSerializableExtra("User");
        MaterialButton reset = (MaterialButton) findViewById(R.id.orderButton);
        reset.setOnClickListener(v -> {
            String pass = password.getText().toString();
            dbref.child("Clients").orderByChild("username").equalTo(user.getUsername()).addValueEventListener(new ValueEventListener() {
                //Check whether username is stored under a client account
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.exists()) {
                        //There exists a client with this username, was the correct password inputed?
                        for (DataSnapshot d : snapshot.getChildren()) {
                            Client client = d.getValue(Client.class);
                            String id = d.getKey();
                            dbref.child("Clients").child(id).child("password").setValue(pass);
                            openLogin();
                        }
                    } else {
                        dbref.child("Cooks").orderByChild("username").equalTo(user.getUsername()).addValueEventListener(new ValueEventListener() {
                            //Check whether username is stored under a cook account
                            @Override
                            public void onDataChange(@NonNull DataSnapshot cooksnapshot) {
                                if (cooksnapshot.exists()) {
                                    for (DataSnapshot cs : cooksnapshot.getChildren()) {
                                        Cook cook = cs.getValue(Cook.class);
                                        String id = cs.getKey();
                                        dbref.child("Cooks").child(id).child("password").setValue(pass);
                                        openLogin();

                                    }
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                    Toast.makeText(getApplicationContext(), "Password reset!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });
    }

    public void openLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}