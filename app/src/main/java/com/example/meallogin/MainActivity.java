package com.example.meallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref = db.getReference();
    Client client;

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

        login.setOnClickListener(v -> {
                    String user = username.getText().toString();
                    String pass = password.getText().toString();
                    if (user.trim().equals("nayna") || user.trim().equals("gdupu") || user.trim().equals("ikarr") || user.trim().equals("ekoro")) {
                        openWelcomeAdminScreen();
                    } else {
                        dbref.child("Clients").orderByChild("username").equalTo(user).addValueEventListener(new ValueEventListener() {
                            //Check whether username is stored under a client account
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.exists()) {
                                    //There exists a client with this username, was the correct password inputed?
                                    dbref.child("Clients").orderByChild("password").equalTo(pass).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()) {
                                                //Correct password
                                                openWelcomeClientScreen();
                                            } else {
                                                //Incorrect password
                                                Toast.makeText(getApplicationContext(), "Account has not been created. Do you want to create it?", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                } else {
                                    dbref.child("Cooks").orderByChild("username").equalTo(user).addValueEventListener(new ValueEventListener() {
                                        //Check whether username is stored under a cook account
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot cooksnapshot) {
                                            if (cooksnapshot.exists()) {
                                                dbref.child("Cooks").orderByChild("password").equalTo(pass).addValueEventListener(new ValueEventListener() {
                                                    //There exists a cook account with the username
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot cookDatasnapshot) {
                                                        if (cookDatasnapshot.exists()) {
                                                            //The correct password was inputed
                                                            openWelcomeCookScreen();
                                                        } else {
                                                            //The incorrect password was inputed
                                                            Toast.makeText(getApplicationContext(), "Account has not been created. Do you want to create it?", Toast.LENGTH_LONG).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
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

    private void getdata() {
        dbref.addValueEventListener(new ValueEventListener() {
            //                @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Client value = snapshot.getValue(Client.class);
                client = value;
            }

            //                @Override
            public void onCancelled(@NonNull DatabaseError err) {
                Toast.makeText(MainActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openSignup() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void openWelcomeAdminScreen() {
        Intent intent = new Intent(this, WelcomeAdminScreen.class);
        startActivity(intent);
    }

    public void openWelcomeCookScreen() {
        Intent intent = new Intent(this, WelcomeCookScreen.class);
        startActivity(intent);
    }

    public void openWelcomeClientScreen() {
        Intent intent = new Intent(this, WelcomeClientScreen.class);
        startActivity(intent);
    }


}