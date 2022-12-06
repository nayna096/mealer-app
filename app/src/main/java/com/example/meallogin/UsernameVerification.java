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

public class UsernameVerification extends AppCompatActivity {
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_verification);
        TextView username = (TextView)findViewById(R.id.checkUsername);
        MaterialButton check = (MaterialButton)findViewById(R.id.check);
        check.setOnClickListener(v->{
            String user = username.getText().toString();
            dbref.child("Clients").orderByChild("username").equalTo(user).addValueEventListener(new ValueEventListener() {
                //Check whether username is stored under a client account
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.exists()) {
                        //There exists a client with this username, was the correct password inputed?
                        for (DataSnapshot d : snapshot.getChildren()) {
                            Client client = d.getValue(Client.class);
                            openPasswordReset(client);
                        }
                    } else {
                        dbref.child("Cooks").orderByChild("username").equalTo(user).addValueEventListener(new ValueEventListener() {
                            //Check whether username is stored under a cook account
                            @Override
                            public void onDataChange(@NonNull DataSnapshot cooksnapshot) {
                                if (cooksnapshot.exists()) {
                                    for (DataSnapshot cs : cooksnapshot.getChildren()) {
                                        Cook cook = cs.getValue(Cook.class);
                                        openPasswordReset(cook);
                                    }
                                } else {
                                    //No account has the username, therefore the account does not exist
                                    Toast.makeText(getApplicationContext(), "No account has this username", Toast.LENGTH_LONG).show();
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
        });
    }
    public void openPasswordReset(GeneralUser user){
        Intent intent = new Intent(this,PasswordReset.class);
        intent.putExtra("User",user);
        startActivity(intent);
    }
}