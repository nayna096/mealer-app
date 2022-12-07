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
    DatabaseReference dbref = db.getReference("Cooks");
    Cook cook;

    public MealAdapter(Context context, ArrayList<Meal> mealArrayList, Cook cook) {
        super(context, R.layout.meal_list_item, R.id.RequestedMeal, mealArrayList);
        this.cook = cook;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Meal meal = getItem(position);
        String mealname = meal.getName();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.meal_list_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.RequestedMeal);
        name.setText(mealname);
        TextView price = convertView.findViewById(R.id.MealPrice);
        price.setText(String.valueOf(meal.getPrice()));
        MaterialButton viewMeal = convertView.findViewById(R.id.ViewRequest);
        viewMeal.setOnClickListener(v -> {

            //The page that pops up when you click on an individual meal
            Intent intent = new Intent(getContext(), MealActivity.class);
            intent.putExtra("Name", cook.getMenu().getMeals().get(position).getName());
            intent.putExtra("Price", cook.getMenu().getMeals().get(position).getPrice());
            intent.putExtra("Description", cook.getMenu().getMeals().get(position).getDescription());
            intent.putExtra("Cuisine Type", cook.getMenu().getMeals().get(position).getCuisineType());

            //Turn the Arraylists into Strings
            StringBuffer in = new StringBuffer();
            StringBuffer al = new StringBuffer();
            int n = 0;
            for (String s : cook.getMenu().getMeals().get(position).getIngredients()) {
                in.append(s);
                //Append the word
                n++;
                //if not the last word, add a comma and a space
                if (n == cook.getMenu().getMeals().get(position).getIngredients().size() == false) {
                    in.append(", ");
                }
            }
            n = 0;
            for (String s : cook.getMenu().getMeals().get(position).getAllergens()) {
                al.append(s);
                n++;
                if (n == cook.getMenu().getMeals().get(position).getAllergens().size() == false) {
                    in.append(", ");
                }
            }

            intent.putExtra("Ingredients", in.toString());
            intent.putExtra("Allergens", al.toString());
            intent.putExtra("Cook", cook);
            getContext().startActivity(intent);
        });

        MaterialButton addMeal = convertView.findViewById(R.id.ComplaintButton);
        if (!(cook.getMenu().getMeals().get(position).isOffered())) {
            addMeal.setEnabled(true);
        } else {
            addMeal.setEnabled(false);
        }

        addMeal.setOnClickListener(v -> {
            if (!(cook.getMenu().getMeals().get(position).isOffered()) && cook.getMenu().getMeals().contains(cook.getMenu().findMealByNameInMeals(mealname))) {
                cook.getMenu().getMeals().get(position).setOffered(true);
                dbref.orderByChild("username").equalTo(cook.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot d : snapshot.getChildren()) {
                                //Find the cook in the db to update their menu
                                String id = d.getKey(); //Cook-unique id
                                dbref.child(id).child("menu").child("meals").child(String.valueOf(position)).child("offered").setValue(true);
                                Toast.makeText(getContext(), "The meal is now offered!", Toast.LENGTH_LONG).show();
                                notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else {
                if (cook.getMenu().getMeals().get(position).isOffered()) {
                    Toast.makeText(getContext(), "This meal is already offered", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "This meal does not exist in the meal list", Toast.LENGTH_LONG).show();
                }
            }
        });

        MaterialButton removeMeal = convertView.findViewById(R.id.RateButton);
        if (cook.getMenu().getMeals().get(position).isOffered()) {
            removeMeal.setEnabled(true);
        } else {
            removeMeal.setEnabled(false);
        }
        removeMeal.setOnClickListener(v -> {
            if (cook.getMenu().getMeals().get(position).isOffered()) {
                cook.getMenu().getMeals().get(position).setOffered(false);
                dbref.orderByChild("username").equalTo(cook.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot d : snapshot.getChildren()) {
                                //Find the cook in the db to update their menu
                                String id = d.getKey(); //Cook-unique id
                                dbref.child(id).child("menu").child("meals").child(String.valueOf(position)).child("offered").setValue(false);
                                Toast.makeText(getContext(), "The meal is no longer offered!", Toast.LENGTH_LONG).show();
                                notifyDataSetChanged();

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
            cook.getMenu().deletefromMeals(cook.getMenu().findMealByNameInMeals(mealname));
            dbref.orderByChild("username").equalTo(cook.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot d : snapshot.getChildren()) {
                            //Find the cook in the db to update their menu
                            String id = d.getKey(); //Cook-unique id
                            dbref.child(id).child("menu").setValue(cook.getMenu());
                            Toast.makeText(getContext(), "The meal is successfully deleted", Toast.LENGTH_LONG).show();
                            notifyDataSetChanged();

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
