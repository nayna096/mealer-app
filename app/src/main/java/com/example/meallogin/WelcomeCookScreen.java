package com.example.meallogin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
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
        TextView ratingMessage = (TextView)findViewById(R.id.ratingMessage);
        RatingBar bar = (RatingBar)findViewById(R.id.ratingBar);
        Cook cook = (Cook)i.getSerializableExtra("Cook");
        welcome.setText("Welcome "+cook.getUsername());
        float rating = 0;
        int offered = 0;
        for(int a = 0;a<cook.getMenu().getMeals().size();a++){
            if(cook.getMenu().getMeals().get(a).isOffered()){
                offered++;
                rating+=cook.getMenu().getMeals().get(a).getRating();
            }
        }
        bar.setRating(rating/offered);
        ratingMessage.setText("Your rating: "+String.valueOf(rating/offered));

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
            editMenuButton.setEnabled(false);


            //display suspension
            if(cook.getSuspensionDate().equals("permanent")){
                message.setText("Your account is suspended permanently");
            }else{
                message.setText("Your account is suspended until "+cook.getSuspensionDate());
            }

        }else{
            //non suspended cooks go here
            editMenuButton.setEnabled(true);
            message.setText("No suspension");
        }





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