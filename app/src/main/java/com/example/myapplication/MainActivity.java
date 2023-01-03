package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login = findViewById(R.id.login);
        TextView register = findViewById(R.id.register);
        EditText Eemail = findViewById(R.id.email);
        EditText Epassword = findViewById(R.id.password);



        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(view -> mAuth.signInWithEmailAndPassword(Eemail.getText().toString().trim(), Epassword.getText().toString().trim()).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Intent intent = new Intent(getApplicationContext(), Navigation_side_bar.class);
                Toast.makeText(getApplicationContext(), "Login successfull", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "username and password does not match", Toast.LENGTH_LONG).show();
            }
        }));

        register.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
        });

    }
}