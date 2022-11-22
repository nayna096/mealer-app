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

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Meal> {

    public ListAdapter(Context context, ArrayList<Meal> mealArrayList) {
        super(context, R.layout.meal_list_item, R.id.MealName, mealArrayList);
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Meal meal = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.meal_list_item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.MealName);
        name.setText(meal.getName());
//        TextView cuisineType = convertView.findViewById(R.id.cuisinetype);
//        TextView ingredients = convertView.findViewById(R.id.ingredients);
//        TextView allergens = convertView.findViewById(R.id.allergens);
        TextView price = convertView.findViewById(R.id.MealPrice);


        price.setText(String.valueOf(meal.getPrice()));
        return super.getView(position, convertView, parent);
    }
}
