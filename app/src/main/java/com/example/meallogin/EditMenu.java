package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");

        //region populate Menu LinearLayout
        LinearLayout menuTable = (LinearLayout) findViewById(R.id.menuTable);
        String[] menuNames = cook.getMenuNames();
        TextView textViewMN = new TextView(this);

        for (int i=0; i<cook.getMenuSize(); i++) {
            textViewMN.setText(menuNames[i]);
            menuTable.addView(textViewMN);
        }
        //endregion
    }
}