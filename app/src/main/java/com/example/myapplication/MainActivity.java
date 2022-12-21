package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = findViewById(R.id.login);
        TextView register = findViewById(R.id.register);
        EditText Eemail = findViewById(R.id.email);
        EditText Epassword = findViewById(R.id.password);

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick (View view){
                if (Eemail.getText().toString().trim().equals("abc@gmail.com") && Epassword.getText().toString().trim().equals("abc123")) {
                    Intent intent = new Intent(getApplicationContext(), selectTrip.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Email and password doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
        });

    }
}