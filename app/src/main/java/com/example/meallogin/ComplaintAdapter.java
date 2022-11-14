package com.example.meallogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.button.MaterialButton;

public class ComplaintAdapter extends FirestoreRecyclerAdapter<Complaint, ComplaintAdapter.ComplaintViewHolder> {
    Context context;

    public ComplaintAdapter(@NonNull FirestoreRecyclerOptions<Complaint> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position, @NonNull Complaint complaint) {
        holder.chefName.setText(complaint.getCookUsername());
        holder.complaintDescription.setText(complaint.getDescription());

    }

    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_complaint_item, parent, false);
        return new ComplaintViewHolder(view);
    }

    class ComplaintViewHolder extends RecyclerView.ViewHolder{

        TextView chefName,complaintDescription;
        MaterialButton permaBan,suspend,dismiss;
        EditText date;

        public ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            chefName = itemView.findViewById((R.id.chefName));
            complaintDescription = itemView.findViewById((R.id.complaintDescription));
            permaBan = itemView.findViewById((R.id.permaBan));
            suspend = itemView.findViewById((R.id.suspend));
            dismiss = itemView.findViewById((R.id.dismiss));
            date = itemView.findViewById((R.id.date));
        }
    }
}
