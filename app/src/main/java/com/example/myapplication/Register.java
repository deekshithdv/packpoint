package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText Eusername = findViewById(R.id.username);
        EditText Eemail = findViewById(R.id.email);
        EditText Epassword = findViewById(R.id.password);
        Button register = findViewById(R.id.register);

        TextView login = findViewById(R.id.login);
        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        register.setOnClickListener(view -> {
            String username = Eusername.getText().toString().trim();
            String email = Eemail.getText().toString().trim();
            String password = Epassword.getText().toString().trim();



            if (username.isEmpty()){
                Eusername.setError("Required");
                Eusername.requestFocus();
                return;
            }
            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Eemail.setError("Required");
                Eemail.requestFocus();
                return;
            }
            if (password.isEmpty() || password.length() < 6){
                Epassword.setError("Required and should be minimum of 6 char");
                Epassword.requestFocus();
                return;
            }




            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            User user = new User(username, email, password);
                            System.out.println("##########");
                            System.out.println("##########");
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(task1 -> {

                                        System.out.println("#####wqeqe#####");
                                        System.out.println("######asraf####");

                                        if (task1.isSuccessful()){
                                            Toast.makeText(Register.this, "User created", Toast.LENGTH_LONG).show();
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(Register.this, "Unable to create user", Toast.LENGTH_LONG).show();
                                        }
                                    });

                        }
                        else{
                            Toast.makeText(Register.this, "Unable to create user", Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}