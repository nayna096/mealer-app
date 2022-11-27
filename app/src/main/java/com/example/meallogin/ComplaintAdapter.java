package com.example.meallogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ComplaintAdapter extends ArrayAdapter<Complaint> {
//    Context context;
//    FirebaseDatabase db = FirebaseDatabase.getInstance();
//    DatabaseReference dbref = db.getReference();
//
//    public ComplaintAdapter(@NonNull FirestoreRecyclerOptions<Complaint> options, Context context) {
//        super(options);
//        this.context = context;
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position, @NonNull Complaint complaint) {
//        holder.chefName.setText(complaint.getCook().getUsername());
//        holder.complaintDescription.setText(complaint.getDescription());
//        holder.permaBan.setOnClickListener(v -> {
//            Cook user = complaint.getCook();
//            user.setStatus(true);
//            user.setSuspensionDate("permanent");
//            complaint.action();
//        });
//        holder.suspend.setOnClickListener(v -> {
//            Cook user = complaint.getCook();
//            user.setStatus(true);
//            user.setSuspensionDate(holder.date.getText().toString());
//            complaint.action();
//        });
//        holder.dismiss.setOnClickListener(v -> {
//            complaint.action();
//        });
//
//    }
//
//    @NonNull
//    @Override
//    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_complaint_item2, parent, false);
//        return new ComplaintViewHolder(view);
//    }
//
//    class ComplaintViewHolder extends RecyclerView.ViewHolder{
//
//        TextView chefName,complaintDescription;
//        MaterialButton permaBan,suspend,dismiss;
//        EditText date;
//
//        public ComplaintViewHolder(@NonNull View itemView) {
//            super(itemView);
//            chefName = itemView.findViewById((R.id.chefName));
//            complaintDescription = itemView.findViewById((R.id.complaintDescription));
//            permaBan = itemView.findViewById((R.id.permaBan));
//            suspend = itemView.findViewById((R.id.suspend));
//            dismiss = itemView.findViewById((R.id.dismiss));
//            date = itemView.findViewById((R.id.date));
//        }
//    }
public ComplaintAdapter(Context context, ArrayList<Complaint> complaintArrayList) {
    super(context, R.layout.recycler_complaint_item2, R.id.chefName, complaintArrayList);
}
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Complaint complaint = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_complaint_item2, parent, false);
        }

        //set name of cook that received the complaint
        TextView name = convertView.findViewById(R.id.chefName);
        name.setText(complaint.getCook().getUsername());

        //display the description of the complaint
        TextView description = convertView.findViewById(R.id.complaintDescription);
        description.setText(complaint.getDescription());


//        TextView cuisineType = convertView.findViewById(R.id.cuisinetype);
//        TextView ingredients = convertView.findViewById(R.id.ingredients);
//        TextView allergens = convertView.findViewById(R.id.allergens);
//        TextView price = convertView.findViewById(R.id.MealPrice);



        return super.getView(position, convertView, parent);
    }
}
