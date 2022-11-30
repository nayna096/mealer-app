package com.example.meallogin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        Intent i = getIntent();
        setContentView(R.layout.activity_welcome_cook_screen);
        EditText message = (EditText)findViewById(R.id.MessageBox);
        TextView welcome = (TextView)findViewById(R.id.WelcomeMessage);
        Cook cook = (Cook)i.getSerializableExtra("Cook");
        welcome.setText("Welcome "+cook.getUsername());


        MaterialButton logout = (MaterialButton) findViewById(R.id.logout);
        logout.setOnClickListener(v->{
            openMainActivity();
        });

        //initialize the home button
        MaterialButton home = (MaterialButton) findViewById(R.id.CookHome);
        home.setOnClickListener(v01->{
            openCook(cook);
        });

        //initialize the editMenu button
        MaterialButton editMenuButton = (MaterialButton) findViewById(R.id.editMenuButton);
        editMenuButton.setOnClickListener(v -> {
           openMenu(cook);
        });

        MaterialButton settings = (MaterialButton) findViewById(R.id.CookSettings);
        settings.setOnClickListener(v->{
            openSettings(cook);
        });
        if(cook.getStatus()){

            //disable the buttons, only non-suspended cooks can use
//            editMealListButton.setEnabled(false);
            editMenuButton.setEnabled(false);


            //display suspension
            dbref.child("Complaints").orderByChild("cook").getRef().orderByChild(cook.getUsername()).addValueEventListener(new ValueEventListener() {
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

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            //non suspended cooks go here
//            editMealListButton.setEnabled(true);
            editMenuButton.setEnabled(true);
            message.setText("No suspension");
        }

//        editMealListButton.setOnClickListener(v -> {
//            Intent intent = new Intent(this, EditMealList.class);
//            intent.putExtra("Cook", cook);
//            startActivity(intent);
//
//        });





    }
    public void openSettings(Cook cook){
        Intent intent = new Intent(this, CookSettingsFrag.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }
    public void openCook(Cook cook){
        Intent intent = new Intent(this, WelcomeCookScreen.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }
    public void openMenu(Cook cook){
        Intent intent = new Intent(this, MenuFrag.class);
        intent.putExtra("Cook", cook);
        startActivity(intent);
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}