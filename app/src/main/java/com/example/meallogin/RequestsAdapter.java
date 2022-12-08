package com.example.meallogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RequestsAdapter extends ArrayAdapter<Request> {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref = db.getReference();

    public RequestsAdapter(@NonNull Context context, ArrayList<Request> requestArrayList) {
        super(context, R.layout.recycler_request_item,R.id.chefName, requestArrayList);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Request request = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_request_item, parent, false);
        }

        //set name of cook that received the complaint
        TextView name = convertView.findViewById(R.id.chefName);
        name.setText(request.getClient().getUsername());

        //display the description of the complaint
        TextView description = convertView.findViewById(R.id.mealDescription);
        description.setText(request.getDescription());

        TextView mealName = convertView.findViewById(R.id.mealname);
        mealName.setText(request.getMeal().getName());

        MaterialButton deny = convertView.findViewById(R.id.denyRequest);
        deny.setOnClickListener(v -> {
            //Dismisses the complaints without punishing cooks
            db.getReference("Requests").orderByChild("status").equalTo(false).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        int i = 0;
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (i != position) {
                                i++;//find specific complaint in the listview
                            } else {
                                Request c = ds.getValue(Request.class);
                                c.setStatus(false);//set it to actioned
                                String id = ds.getKey();//find its specific key
                                db.getReference("Requests").child(id).setValue(c); //overwrite old object
                                remove(request);//remove from listview
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
        MaterialButton accept = convertView.findViewById(R.id.acceptRequest);
        accept.setOnClickListener(v -> {
            //Dismisses the complaints without punishing cooks
            db.getReference("Requests").orderByChild("status").equalTo(false).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        int i = 0;
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (i != position) {
                                i++;//find specific complaint in the listview
                            } else {
                                Request c = ds.getValue(Request.class);
                                c.setStatus(true);//set it to actioned
                                String id = ds.getKey();//find its specific key
                                db.getReference("Requests").child(id).setValue(c); //overwrite old object
                                remove(request);//remove from listview
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

        return super.getView(position, convertView, parent);
    }

}

