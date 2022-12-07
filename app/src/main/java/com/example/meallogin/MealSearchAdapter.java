package com.example.meallogin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MealSearchAdapter extends ArrayAdapter<Tuple> {
    Client client;
    public MealSearchAdapter(@NonNull Context context, ArrayList<Tuple> mealList, Client client) {
        super(context, R.layout.recycler_meal_item, R.id.chefName, mealList);
        this.client = client;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Meal meal = getItem(position).getMeal();
        Cook cook = getItem(position).getCook();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_meal_item, parent, false);
        }
        TextView MealName = convertView.findViewById(R.id.mealname);
        MealName.setText(meal.getName());

        TextView Chefname = convertView.findViewById(R.id.chefName);
        Chefname.setText(cook.toString());

        TextView description = convertView.findViewById(R.id.mealDescription);
        description.setText(meal.getDescription());

        MaterialButton details = convertView.findViewById(R.id.MealDetails);
        MaterialButton add = convertView.findViewById(R.id.addToCart);
        MaterialButton remove = convertView.findViewById(R.id.removeFromCart);

        details.setOnClickListener(v->{
            Intent intent = new Intent(getContext(),MealPreview.class);
            intent.putExtra("Meal",meal);
            intent.putExtra("Cook",cook);
            intent.putExtra("Client",client);
            getContext().startActivity(intent);
        });
        add.setOnClickListener(v->{
            Intent intent = new Intent(getContext(),RequestDescriptionPage.class);
            intent.putExtra("Meal",meal);
            intent.putExtra("Cook",cook);
            intent.putExtra("Client",client);
            intent.putExtra("Origin",SearchMeals.class);
            getContext().startActivity(intent);
        });
        return super.getView(position, convertView, parent);
    }
}


