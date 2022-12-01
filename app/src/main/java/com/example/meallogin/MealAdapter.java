package com.example.meallogin;

import android.content.Context;
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
        MaterialButton delete = convertView.findViewById(R.id.DeleteMeal);
        delete.setEnabled(true);
        delete.setOnClickListener(v -> {
            this.cook.getMenu().removefromOffered(meal);
            this.cook.getMenu().getMeallist().remove(meal);
            db.getReference("Cooks").orderByChild("username").equalTo(cook.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        int i = 0;
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (i != position) {
                                i++;//find specific meal in the listview
                            } else {
                                Cook c = ds.getValue(Cook.class);
                                c.getMenu().removefromOffered(meal);
                                c.getMenu().getMeallist().remove(meal);
                                String id = ds.getKey();//find its specific key
                                db.getReference("Cooks").child(id).setValue(c); //overwrite old object
                                remove(meal);//remove from listview
                                Toast.makeText(getContext(), "The meal is successfully deleted!", Toast.LENGTH_LONG).show();
                                notifyDataSetChanged();//update listview
                                break;
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });

        MaterialButton addMeal = convertView.findViewById(R.id.AddMeal);
        if (cook.getMenu().getOffered().contains(cook.getMenu().findMealByNameInOffered(mealname))) {
            addMeal.setEnabled(false);
        } else {
            addMeal.setEnabled(true);
        }
        addMeal.setOnClickListener(v -> {
//            if (!(cook.getMenu().getOffered().contains(cook.getMenu().findMealByNameInOffered(mealname))) && cook.getMenu().getMeallist().contains(cook.getMenu().findMealByNameInMeallist(mealname))) {

            dbref.child("Cooks").orderByChild("username").equalTo(cook.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot d : snapshot.getChildren()) {
                            Cook c = d.getValue(Cook.class);
                            if (c.getUsername().equals(cook.getUsername())) {
                                //Find the cook in the db to update their menu
                                c.getMenu().addtoOffered(c.getMenu().findMealByNameInMeallist(mealname));
                                String id = d.getKey(); //Cook-unique id
                                dbref.child("Cooks").child(id).setValue(c);
                                Toast.makeText(getContext(), "The meal is now offered!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
//            } else {
//                if (cook.getMenu().getOffered().contains(cook.getMenu().findMealByNameInOffered(mealname)))
//                    Toast.makeText(getContext(), "This meal is already offered", Toast.LENGTH_LONG).show();
//                if(!(cook.getMenu().getMeallist().contains(cook.getMenu().findMealByNameInMeallist(mealname))))
//                    Toast.makeText(getContext(), "This meal does not exist in the meal list", Toast.LENGTH_LONG).show();
//            }
        });

        MaterialButton removeMeal = convertView.findViewById(R.id.RemoveMeal);
        if (!(cook.getMenu().getOffered().contains(cook.getMenu().findMealByNameInOffered(mealname)))) {
            removeMeal.setEnabled(false);
        } else {
            removeMeal.setEnabled(true);
        }
        removeMeal.setOnClickListener(v -> {
//            if (cook.getMenu().getOffered().contains(cook.getMenu().findMealByNameInOffered(mealname))) {
            dbref.child("Cooks").orderByChild("username").equalTo(cook.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot d : snapshot.getChildren()) {
                            Cook c = d.getValue(Cook.class);
                            if (c.getUsername().equals(cook.getUsername())) {
                                //Find the cook in the db to update their menu
                                c.getMenu().removefromOffered(c.getMenu().findMealByNameInOffered(mealname));
                                String id = d.getKey(); //Cook-unique id
                                dbref.child("Cooks").child(id).setValue(c);
                                Toast.makeText(getContext(), "The meal is no longer offered!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
//            } else {
//                Toast.makeText(getContext(), "No such meal is offered", Toast.LENGTH_LONG).show();
//            }
        });
        return super.getView(position, convertView, parent);
    }
}
