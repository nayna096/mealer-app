package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class WelcomeClientScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_client_screen);
        Client client = (Client) getIntent().getSerializableExtra("Client");
        MaterialButton logout = (MaterialButton) findViewById(R.id.logout);
        TextView welcome = (TextView)findViewById(R.id.WelcomeMessage);
        welcome.setText("Welcome "+client.getUsername());
        logout.setOnClickListener(v->
        {
            openMainActivity();
        });
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}