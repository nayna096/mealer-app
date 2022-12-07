package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
        MaterialButton home = (MaterialButton) findViewById(R.id.Home);
        home.setOnClickListener(v -> {
            openWelcomeClient(client);
        });
        MaterialButton search = (MaterialButton) findViewById(R.id.search);
        search.setOnClickListener(v -> {
            openSearchMeals(client);
        });
        MaterialButton settings = (MaterialButton) findViewById(R.id.settings);
        settings.setOnClickListener(v -> {
            openClientSettingsFrag(client);
        });

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Requests").orderByChild("status").equalTo(false).getRef();
        dbref.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<Request> outstanding = new ArrayList<>();
                for(DataSnapshot snapshot: task.getResult().getChildren()){
                    Request request = snapshot.getValue(Request.class);
                    outstanding.add(request);
                }
                ClientRequestAdapter cra = new ClientRequestAdapter(WelcomeClientScreen.this,outstanding);
                ListView requestView = (ListView) findViewById(R.id.RequestView);
                requestView.setAdapter(cra);
            }
        });

    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openWelcomeClient(Client client) {
        Intent intent = new Intent(this, WelcomeClientScreen.class);
        intent.putExtra("Client", client);
        startActivity(intent);
    }
    public void openClientSettingsFrag(Client client) {
        Intent intent = new Intent(this, ClientSettingsFrag.class);
        intent.putExtra("Client", client);
        startActivity(intent);
    }
    public void openSearchMeals(Client client) {
        Intent intent = new Intent(this, SearchMeals.class);
        intent.putExtra("Client", client);
        startActivity(intent);
    }
}