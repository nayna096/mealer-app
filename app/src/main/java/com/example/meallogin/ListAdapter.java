package com.example.meallogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {
    public ListAdapter(Context context, ArrayList<String> list) {
        super(context, R.layout.activity_edit_meal_list, list);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Object mealName = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_edit_meal_list, parent, false);
        }

        return super.getView(position, convertView, parent);
    }
}
