package com.example.meallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    private String username;
    private String pw;
    private String confirmpw;
    private String email;

    String [] restrictedUsers =  {"nayna","gdupu","ikarr","ekoro"};
    int ret = 0;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbref = db.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText user = (EditText) findViewById(R.id.username);
        EditText Password = (EditText) findViewById(R.id.password);
        EditText Confirmp = (EditText) findViewById(R.id.confirmp);
        EditText Email = (EditText) findViewById(R.id.email);
        MaterialButton login = (MaterialButton) findViewById(R.id.login);
        MaterialButton cook = (MaterialButton) findViewById(R.id.Cook);
        MaterialButton client = (MaterialButton) findViewById(R.id.Client);

        username = user.getText().toString();
        pw = Password.getText().toString();
        confirmpw = Confirmp.getText().toString();
        email = Email.getText().toString();

        for(int i = 0;i<restrictedUsers.length;i++){
            if(user.getText().toString().equals(restrictedUsers[i])){
                Toast.makeText(getApplicationContext(),"This username is invalid due to an admin conflict",Toast.LENGTH_LONG).show();
                user.setText("");
            }
        }
        login.setOnClickListener(v -> openMainActivity());
        client.setOnClickListener(v ->
        {
            for(int i = 0;i<restrictedUsers.length;i++){
                if(user.getText().toString().equals(restrictedUsers[i])){
                    Toast.makeText(getApplicationContext(),"This username is invalid due to an admin conflict",Toast.LENGTH_LONG).show();
                    user.setText("");
                }
            }
            dbref.child("Cooks").orderByChild("username").equalTo(user.getText().toString().trim()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        Toast.makeText(getApplicationContext(),"This username is already used by a cook, use a different one", Toast.LENGTH_LONG).show();
                        user.setText("");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            dbref.child("Clients").orderByChild("username").equalTo(user.getText().toString().trim()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    if(datasnapshot.exists()){
                        Toast.makeText(getApplicationContext(),"This username is already used by a client, use a different one", Toast.LENGTH_LONG).show();
                        user.setText("");
                    }else{
                        createClient(user,Password,Confirmp,Email);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        });
        cook.setOnClickListener(v ->
        {
            for(int i = 0;i<restrictedUsers.length;i++){
                if(user.getText().toString().equals(restrictedUsers[i])){
                    Toast.makeText(getApplicationContext(),"This username is invalid due to an admin conflict",Toast.LENGTH_LONG).show();
                    user.setText("");
                }
            }
            dbref.child("Clients").orderByChild("username").equalTo(user.getText().toString().trim()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        Toast.makeText(getApplicationContext(), "This username is already used by a client, use a different one", Toast.LENGTH_LONG).show();
                        user.setText("");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            dbref.child("Cooks").orderByChild("username").equalTo(user.getText().toString().trim()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    if(datasnapshot.exists()){
                        Toast.makeText(getApplicationContext(),"This username is already used by a cook, use a different one", Toast.LENGTH_LONG).show();
                        user.setText("");
                    }else{
                        createCook(user, Password, Confirmp, Email);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });

    }

    public void createClient(EditText user, EditText Password, EditText Confirmp, EditText Email){
        if (!(TextUtils.isEmpty(user.getText().toString()) || TextUtils.isEmpty(Password.getText().toString()) || TextUtils.isEmpty(Email.getText().toString()))) {
            if (Confirmp.getText().toString().trim().equals(Password.getText().toString().trim())) {
                Client newclient = new Client(user.getText().toString(), Password.getText().toString(), Email.getText().toString());
                DatabaseReference clientref = db.getReference("Clients");
                String id = clientref.push().getKey();
                clientref.child(id).setValue(newclient);
                openWelcomeClientScreen();
            } else {
                Toast.makeText(getApplicationContext(), "Your password does not match", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "One of the fields is empty", Toast.LENGTH_LONG).show();
        }
    }
    public void createCook(EditText user, EditText Password, EditText Confirmp, EditText Email){
        if (!(TextUtils.isEmpty(user.getText().toString()) || TextUtils.isEmpty(Password.getText().toString()) || TextUtils.isEmpty(Email.getText().toString()))) {
            if (Confirmp.getText().toString().trim().equals(Password.getText().toString().trim())) {
                Cook newcook = new Cook(user.getText().toString(), Password.getText().toString(), Email.getText().toString());
                DatabaseReference cookref = db.getReference("Cooks");
                String id = cookref.push().getKey();
                cookref.child(id).setValue(newcook);
                openWelcomeCookScreen(newcook);
            }else{
                Toast.makeText(getApplicationContext(), "Your password does not match", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "One of the fields is empty", Toast.LENGTH_LONG).show();
        }
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openWelcomeCookScreen(Cook c) {
        Intent intent = new Intent(this, WelcomeCookScreen.class);
        intent.putExtra("Cook", c);
        startActivity(intent);
    }

    public void openWelcomeClientScreen() {
        Intent intent = new Intent(this, WelcomeClientScreen.class);
        startActivity(intent);
    }

    public static String validate(String un, String pw, String cpw, String email) {
        if (!un.equals("ekoro") && !un.equals("gdupu") && !un.equals("nayna") && !un.equals("ikarr") && !un.equals("admin") && pw.equals(cpw) && !email.equals("")) {
            return "Success";
        }
        return "Login failed";
    }
}