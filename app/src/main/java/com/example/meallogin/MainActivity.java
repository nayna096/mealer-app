package com.example.meallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref = db.getReference();
    Administrator admin = new Administrator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton login = (MaterialButton) findViewById(R.id.login);
        MaterialButton signup = (MaterialButton) findViewById(R.id.signup);
        MaterialButton forgot = (MaterialButton) findViewById(R.id.forgotpassword);
        //admin

        FirebaseDatabase cooksdb = dbref.child("Cooks").getDatabase();
        cooksdb.getReference().orderByChild("username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> it = snapshot.child("Cooks").getChildren().iterator();

                for (DataSnapshot postSnapshot : snapshot.child("Cooks").getChildren()) {
                    if (it.hasNext()) {
                        Cook c = it.next().getValue(Cook.class);
                        createComplaint(c.getEmail(), c);
                    } else {
                        break;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        login.setOnClickListener(v -> {
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    if (user.trim().equals("admin") && pass.trim().equals("admin")) {
                        openWelcomeAdminScreen();
                    } else {
                        dbref.child("Clients").orderByChild("username").equalTo(user).addValueEventListener(new ValueEventListener() {
                            //Check whether username is stored under a client account
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.exists()) {
                                    //There exists a client with this username, was the correct password inputed?
                                    for (DataSnapshot d : snapshot.getChildren()) {
                                        Client client = d.getValue(Client.class);
                                        if (client.getPassword().equals(pass)) {
                                            //Correct password
                                            openWelcomeClientScreen();
                                        } else {
                                            //Incorrect password
                                            Toast.makeText(getApplicationContext(), "Account has not been created. Do you want to create it?", Toast.LENGTH_LONG).show();

                                        }
                                    }
                                } else {
                                    dbref.child("Cooks").orderByChild("username").equalTo(user).addValueEventListener(new ValueEventListener() {
                                        //Check whether username is stored under a cook account
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot cooksnapshot) {
                                            if (cooksnapshot.exists()) {
                                                for (DataSnapshot cs : cooksnapshot.getChildren()) {
                                                    Cook cook = cs.getValue(Cook.class);
                                                    if (cook.getPassword().equals(pass)) {
                                                        //Correct password
                                                        openWelcomeCookScreen(cook);
                                                    } else {
                                                        //Incorrect password
                                                        Toast.makeText(getApplicationContext(), "Account has not been created. Do you want to create it?", Toast.LENGTH_LONG).show();

                                                    }
                                                }
                                            } else {
                                                //No account has the username, therefore the account does not exist
                                                Toast.makeText(getApplicationContext(), "Account has not been created. Do you want to create it?", Toast.LENGTH_LONG).show();
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
                //In the future here the user that is logging in will be read from firebase, to determine
                //whether they are a client or cook. The corresponding welcome screen will then be opened
                //Done! (01/11/2022)

//                openWelcomeScreen()
        );
        signup.setOnClickListener(v -> {
            openSignup();
        });
        forgot.setOnClickListener(v ->
        {
            //Read from database to find username/email, then update password
        });

    }

    public Complaint createComplaint(String description, Cook cook) {
        Complaint complaint = new Complaint(description, cook);
        DatabaseReference complaintref = db.getReference("Complaints");
        String id = complaintref.push().getKey();
        complaintref.child(id).setValue(complaint);
        return complaint;
    }

    public void openSignup() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }


    public void openWelcomeAdminScreen() {
        Intent intent = new Intent(this, WelcomeAdminScreen.class);
        intent.putExtra("Admin", admin);
        startActivity(intent);
    }

    public void openWelcomeCookScreen(Cook c) {
        Intent intent = new Intent(this, WelcomeCookScreen.class);
        intent.putExtra("Cook", c);
        startActivity(intent);
    }

    public void openWelcomeClientScreen() {
        Intent intent = new Intent(this, WelcomeClientScreen.class);

        startActivity(intent);
    }

    public static String validate(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return "Success";
        } else {
            return "Login failed";
        }
    }

}