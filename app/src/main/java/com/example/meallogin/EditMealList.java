package com.example.meallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.meallogin.databinding.ActivityEditMealListBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EditMealList extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref= db.getReference();
    ActivityEditMealListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = EditMealList
        binding = ActivityEditMealListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Cook cook = (Cook) getIntent().getSerializableExtra("Cook");
        ListAdapter listAdapter = new ListAdapter(EditMealList.this,cook.getMenu().getMeallist());

        binding.mealListTable.setClickable(true);
        binding.mealListTable.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //The page that pops up when you click on an individual meal
                Intent intent = new Intent(EditMealList.this,MealActivity.class);
                intent.putExtra("Name", cook.getMenu().mealListNames().get(position));
                intent.putExtra("Price",cook.getMenu().getMeallist().get(position).getPrice());
                intent.putExtra("Description", cook.getMenu().getMeallist().get(position).getDescription());
                intent.putExtra("Cuisine Type",cook.getMenu().getMeallist().get(position).getCuisineType());

                //Turn the Arraylists into Strings
                StringBuffer in = new StringBuffer();
                StringBuffer al = new StringBuffer();
                int n = 0;
                for(String s: cook.getMenu().getMeallist().get(position).getIngredients()){
                    in.append(s);
                    //Append the word
                    n++;
                    //if not the last word, add a comma and a space
                    if(n== cook.getMenu().getMeallist().get(position).getIngredients().size()==false){
                        in.append(", ");
                    }
                }
                n = 0;
                for(String s:cook.getMenu().getMeallist().get(position).getAllergens()){
                    al.append(s);
                    n++;
                    if(n== cook.getMenu().getMeallist().get(position).getAllergens().size()==false){
                        in.append(", ");
                    }
                }

                intent.putExtra("Ingredients", in.toString());
                intent.putExtra("Allergens", al.toString());
                intent.putExtra("Previous Class", EditMealList.class);
                intent.putExtra("Cook",cook);
                startActivity(intent);
            }
        });

        MaterialButton createMealButton = (MaterialButton) findViewById(R.id.mealListCreateNewMealButton);
        MaterialButton deleteMealButton = (MaterialButton) findViewById(R.id.mealListDeleteMealButton);
        MaterialButton back = (MaterialButton)findViewById(R.id.mealListBackButton);

        //Activate the list of meals
        binding.mealListTable.setAdapter(listAdapter);

        //Make them clickable
        binding.mealListTable.setClickable(true);
        createMealButton.setOnClickListener(v1 -> {
            Intent intent = new Intent(this, CreateMeal.class);
            intent.putExtra("Cook", cook);
            startActivity(intent);
        });

        deleteMealButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, DeleteMeal.class);
            intent.putExtra("Cook", cook);
            startActivity(intent);
        });
        back.setOnClickListener(v2->{
            Intent intent = new Intent(this, WelcomeCookScreen.class);
            intent.putExtra("Cook",cook);
            startActivity(intent);
        });
    }

    public static boolean isSuspended(Cook cook) {
        return cook.getStatus();
    }
}