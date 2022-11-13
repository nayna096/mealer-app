package com.example.meallogin;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ComplaintAdapter extends FirestoreRecyclerAdapter<Complaint, ComplaintAdapter.ComplaintViewHolder> {


    public ComplaintAdapter(@NonNull FirestoreRecyclerOptions<Complaint> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position, @NonNull Complaint model) {

    }

    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    class ComplaintViewHolder extends RecyclerView.ViewHolder{

        TextView chefName,complaintDescription;

        public ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            chefName = itemView.findViewById((R.id.chefName));
            complaintDescription = itemView.findViewById((R.id.complaintDescription));
        }
    }
}
