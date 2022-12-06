package com.example.meallogin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MealAdapter extends ArrayAdapter<Meal> {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref = db.getReference();
    Cook cook;

    public MealAdapter(Context context, ArrayList<Meal> mealArrayList, Cook cook) {
        super(context, R.layout.meal_list_item, R.id.MealName, mealArrayList);
        this.cook = cook;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Meal meal = getItem(position);
        String mealname = meal.getName();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.meal_list_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.MealName);
        name.setText(mealname);
        TextView price = convertView.findViewById(R.id.MealPrice);
        price.setText(String.valueOf(meal.getPrice()));
        MaterialButton viewMeal = convertView.findViewById(R.id.ViewMeal);
        viewMeal.setOnClickListener(v->{

            //The page that pops up when you click on an individual meal
            Intent intent = new Intent(getContext(), MealActivity.class);
            intent.putExtra("Name", cook.getMenu().getMeallist().get(position).getName());
            intent.putExtra("Price", cook.getMenu().getMeallist().get(position).getPrice());
            intent.putExtra("Description", cook.getMenu().getMeallist().get(position).getDescription());
            intent.putExtra("Cuisine Type", cook.getMenu().getMeallist().get(position).getCuisineType());

            //Turn the Arraylists into Strings
            StringBuffer in = new StringBuffer();
            StringBuffer al = new StringBuffer();
            int n = 0;
            for (String s : cook.getMenu().getMeallist().get(position).getIngredients()) {
                in.append(s);
                //Append the word
                n++;
                //if not the last word, add a comma and a space
                if (n == cook.getMenu().getMeallist().get(position).getIngredients().size() == false) {
                    in.append(", ");
                }
            }
            n = 0;
            for (String s : cook.getMenu().getMeallist().get(position).getAllergens()) {
                al.append(s);
                n++;
                if (n == cook.getMenu().getMeallist().get(position).getAllergens().size() == false) {
                    in.append(", ");
                }
            }

            intent.putExtra("Ingredients", in.toString());
            intent.putExtra("Allergens", al.toString());
            intent.putExtra("Previous Class", EditMenu.class);
            intent.putExtra("Cook", cook);
            getContext().startActivity(intent);
        });
        MaterialButton addMeal = convertView.findViewById(R.id.AddMeal);
        if (!(cook.getMenu().getOffered().contains(cook.getMenu().findMealByNameInOffered(mealname)))) {
            addMeal.setEnabled(true);
        } else {
            addMeal.setEnabled(false);
        }

        addMeal.setOnClickListener(v -> {
            if (!(cook.getMenu().getOffered().contains(cook.getMenu().findMealByNameInOffered(mealname))) && cook.getMenu().getMeallist().contains(cook.getMenu().findMealByNameInMeallist(mealname))) {
                cook.getMenu().addtoOffered(cook.getMenu().findMealByNameInMeallist(mealname));
                dbref.child("Cooks").orderByChild("username").equalTo(cook.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot d : snapshot.getChildren()) {
                                Cook c = d.getValue(Cook.class);
                                if (c.getUsername().equals(cook.getUsername())) {
                                    //Find the cook in the db to update their menu
                                    String id = d.getKey(); //Cook-unique id
                                    dbref.child("Cooks").child(id).child("menu").setValue(cook.getMenu());
                                    Toast.makeText(getContext().getApplicationContext(), "The meal is now offered!", Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else {
                if (cook.getMenu().getOffered().contains(cook.getMenu().findMealByNameInOffered(mealname)))
                    Toast.makeText(getContext(), "This meal is already offered", Toast.LENGTH_LONG).show();
                if (!(cook.getMenu().getMeallist().contains(cook.getMenu().findMealByNameInMeallist(mealname))))
                    Toast.makeText(getContext(), "This meal does not exist in the meal list", Toast.LENGTH_LONG).show();
            }
        });

        MaterialButton removeMeal = convertView.findViewById(R.id.RemoveMeal);
        if(cook.getMenu().getOffered().contains(cook.getMenu().findMealByNameInOffered(mealname))){
            removeMeal.setEnabled(true);
        }else{
            removeMeal.setEnabled(false);
        }
        removeMeal.setOnClickListener(v -> {
            if (cook.getMenu().getOffered().contains(cook.getMenu().findMealByNameInOffered(mealname))) {
                cook.getMenu().removefromOffered(cook.getMenu().findMealByNameInOffered(mealname));
                dbref.child("Cooks").orderByChild("username").equalTo(cook.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot d : snapshot.getChildren()) {
                                Cook c = d.getValue(Cook.class);
                                if (c.getUsername().equals(cook.getUsername())) {
                                    //Find the cook in the db to update their menu
                                    String id = d.getKey(); //Cook-unique id
                                    dbref.child("Cooks").child(id).child("menu").setValue(cook.getMenu());
                                    Toast.makeText(getContext().getApplicationContext(), "The meal is no longer offered!", Toast.LENGTH_LONG).show();
                                    notifyDataSetChanged();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else {
                Toast.makeText(getContext(), "No such meal is offered", Toast.LENGTH_LONG).show();
            }
        });

        MaterialButton deleteMeal = convertView.findViewById(R.id.DeleteMeal);
        deleteMeal.setOnClickListener(v -> {
            //Ensure that the meal actually exists in the Meal List
            cook.getMenu().removefromOffered(cook.getMenu().findMealByNameInOffered(mealname));
            cook.getMenu().deletefromMeallist(cook.getMenu().findMealByNameInMeallist(mealname));

            dbref.child("Cooks").orderByChild("username").equalTo(cook.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot d : snapshot.getChildren()) {
                            Cook c = d.getValue(Cook.class);
                            if (c.getUsername().equals(cook.getUsername())) {
                                //Find the cook in the db to update their menu
                                String id = d.getKey(); //Cook-unique id
                                dbref.child("Cooks").child(id).child("menu").setValue(cook.getMenu());
                                Toast.makeText(getContext(), "The meal is successfully deleted", Toast.LENGTH_LONG).show();
                                notifyDataSetChanged();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });

        return super.getView(position, convertView, parent);
    }
}
