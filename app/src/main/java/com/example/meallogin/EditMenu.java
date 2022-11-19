package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EditMenu extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref= db.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");

        //region populate Menu LinearLayout
        LinearLayout menuTable = (LinearLayout) findViewById(R.id.menuTable);
        List<String> menuNames = cook.getMenu().MenuNames();
        TextView textViewMN = new TextView(this);

        for (int i=0; i<cook.getMenu().MenuSize(); i++) {
            textViewMN.setText(menuNames.get(i));
            menuTable.addView(textViewMN);
        }
        //endregion
    }
}