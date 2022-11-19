package com.example.meallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeCookScreen extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref= db.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_cook_screen);
        EditText message = (EditText)findViewById(R.id.MessageBox);
        Cook c = (Cook)getIntent().getSerializableExtra("Cook");
        MaterialButton logout = (MaterialButton) findViewById(R.id.logout);
        if(c.isSuspended()){
            dbref.child("Complaints").orderByChild("cookUsername").equalTo(c.getUsername()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for(DataSnapshot ds: snapshot.getChildren()){
                            Complaint complaint = ds.getValue(Complaint.class);
                            if(complaint.getSuspensionDate().equals("permanent")){
                                message.setText("Your cook account is suspended permanently");
                            }else{
                                message.setText("Your cook account is suspended until "+complaint.getCook().getSuspensionDate());
                            }
                        }

                    }else{
                        message.setText("No suspension");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{

            //Here is where non-suspended cooks go, so this is where the functionality to add/delete meals must go
            MaterialButton editMealListButton = (MaterialButton) findViewById(R.id.editMealListButton);
            MaterialButton editMenuButton = (MaterialButton) findViewById(R.id.editMenuButton);

            editMealListButton.setOnClickListener(v -> {
                setContentView(R.layout.activity_edit_meal_list_screen);
            });

            editMenuButton.setOnClickListener(v -> {
                setContentView(R.layout.activity_edit_menu_screen);
            });
        }
//        message.setText(c.getUsername());
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