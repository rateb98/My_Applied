package com.example.myapplied;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplied.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        mAuth = FirebaseAuth.getInstance();
    }

    public void CreateAccount(View view) {
        Register();
    }

    private void Register() {
        EditText edEmail = findViewById(R.id.edEmail);
        EditText edPassword = findViewById(R.id.edPassword);
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task ->
                    {
                        if (task.isSuccessful()) {
                            FirebaseUser User = mAuth.getCurrentUser();
                            String ui=User.getUid();
                            myRef = FirebaseDatabase.getInstance().getReference("Users").child(ui);
                            Users newUser =new Users(ui,"def","def","def","def","def","0");
                            myRef.setValue(newUser).addOnCompleteListener(this, task1 -> { });
                            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                            SendEmailVerification();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "الرجاء ملء خانة البريد الإلكتروني و كلمة المرور", Toast.LENGTH_SHORT).show();
        }
    }

    private void SendEmailVerification() {
        FirebaseUser User = mAuth.getCurrentUser();
        User.sendEmailVerification().addOnCompleteListener(this, task ->
                {
                    if (task.isSuccessful()) {
                        Intent Login = new Intent(this, com.example.myapplied.Login.class);
                        startActivity(Login);
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}



