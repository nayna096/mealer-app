package com.example.meallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");
        MaterialButton logout = (MaterialButton) findViewById(R.id.logout);

        if(cook.isSuspended()){
            dbref.child("Complaints").orderByChild("cookUsername").equalTo(cook.getUsername()).addValueEventListener(new ValueEventListener() {
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
        } else {
            //TODO Isam
            //Here is where non-suspended cooks go, so this is where the functionality to add/delete meals must go
            MaterialButton editMealListButton = (MaterialButton) findViewById(R.id.editMealListButton);
            MaterialButton editMenuButton = (MaterialButton) findViewById(R.id.editMenuButton);

            editMealListButton.setOnClickListener(v -> {
                setContentView(R.layout.activity_edit_meal_list_screen);

                //region populate MealList LinearLayout
                LinearLayout mealListTable = (LinearLayout) findViewById(R.id.mealListTable);
                String[] mealListNames = cook.getMealListNames();
                TextView textViewML = new TextView(this);

                for (int i=0; i<cook.getMealListSize(); i++) {
                    textViewML.setText(mealListNames[i]);
                    mealListTable.addView(textViewML);
                }
                //endregion

                MaterialButton createMealButton = (MaterialButton) findViewById(R.id.mealListCreateNewMealButton);
                MaterialButton deleteMealButton = (MaterialButton) findViewById(R.id.mealListDeleteMealButton);

                createMealButton.setOnClickListener(v1 -> {
                    setContentView(R.layout.activity_create_meal_screen); //TODO Lisa
                });
            });

            editMenuButton.setOnClickListener(v -> {
                setContentView(R.layout.activity_edit_menu_screen);

                //region populate Menu LinearLayout
                LinearLayout menuTable = (LinearLayout) findViewById(R.id.menuTable);
                String[] menuNames = cook.getMenuNames();
                TextView textViewMN = new TextView(this);

                for (int i=0; i<cook.getMenuSize(); i++) {
                    textViewMN.setText(menuNames[i]);
                    menuTable.addView(textViewMN);
                }
                //endregion


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