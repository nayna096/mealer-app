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

public class ClientRequestAdapter extends ArrayAdapter<Request> {
    public ClientRequestAdapter(@NonNull Context context, ArrayList<Request> requests) {
        super(context, R.layout.client_request_item, R.id.RequestedMeal, requests);
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Request request = getItem(position);
        Meal meal = request.getMeal();
        Cook cook = request.getCook();
        Client client = request.getClient();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.client_request_item, parent, false);
        }

        TextView mealName = (TextView)convertView.findViewById(R.id.RequestedMeal);
        mealName.setText(request.toString());

        TextView cookName = (TextView) convertView.findViewById(R.id.RequestedCook);
        cookName.setText("by "+cook.getUsername());

        MaterialButton complaint = (MaterialButton) convertView.findViewById(R.id.ComplaintButton);

        complaint.setOnClickListener(v->{
            Intent intent = new Intent(getContext(),ComplaintDescriptionPage.class);
            intent.putExtra("Meal",meal);
            intent.putExtra("Cook",cook);
            getContext().startActivity(intent);
        });
        MaterialButton rate = (MaterialButton) convertView.findViewById(R.id.RateButton);
        if(request.isStatus()==false){
            rate.setEnabled(false);
        }
        rate.setOnClickListener(v->{
//            Intent intent = new Intent(getContext(),);
//            intent.putExtra("Request", request);
//            getContext().startActivity(intent);
        });




        return super.getView(position, convertView, parent);
    }
}
