package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();

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
//                    db.getReference("Clients").
                    if (username.getText().toString().trim().equals("nayna") || username.getText().toString().trim().equals("gdupu") || username.getText().toString().trim().equals("ikarr") || username.getText().toString().trim().equals("ekoro")) {
                        openWelcomeAdminScreen();

                    }

                }
                //In the future here the user that is logging in will be read from firebase, to determine
                //whether they are a client or cook. The corresponding welcome screen will then be opened

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