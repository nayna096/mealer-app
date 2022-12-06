package com.example.meallogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MealSearchAdapter extends ArrayAdapter<Meal> {
    public MealSearchAdapter(@NonNull Context context, ArrayList<Meal> mealList) {
        super(context, R.layout.recycler_meal_item, R.id.chefName, mealList);
    }
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            Meal meal = getItem(position);

                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_meal_item, parent, false);
                }

                TextView name = convertView.findViewById(R.id.chefName);
                name.setText(meal.getName());

                TextView description = convertView.findViewById(R.id.mealDescription);
                description.setText(meal.getDescription());
            return super.getView(position, convertView, parent);
        }
        }


