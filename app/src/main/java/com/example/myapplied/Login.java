package com.example.myapplied;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

    }

    public void LoginAccount(View view) {
       Login();
    }

    private void Login()
    {
        EditText edEmail = findViewById(R.id.edLoginEmail);
        EditText edPassword = findViewById(R.id.edLoginPassword);
        String email=edEmail.getText().toString();
        String password=edPassword.getText().toString();
        if (!email.isEmpty()&&!password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            VerifyEmailAddress();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else {
            Toast.makeText(getApplicationContext(), "الرجاء ملء خانة البريد الإلكتروني و كلمة المرور",Toast.LENGTH_SHORT).show();
        }
    }
    public void VerifyEmailAddress()
    {
        FirebaseUser CurUser=mAuth.getCurrentUser();
        String userId=CurUser.getUid();
        myRef = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("ready");
        if(CurUser.isEmailVerified()) {
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if(snapshot.getValue().toString().equals("0"))
                   {
                       Intent Account_completion = new Intent(getApplicationContext(), com.example.myapplied.Account_completion.class);
                       startActivity(Account_completion);
                   }
                   else if(snapshot.getValue().toString().equals("-1")||snapshot.getValue().toString().equals("-2"))
                   {
                       Intent Waiting_for_acceptance = new Intent(getApplicationContext(), com.example.myapplied.Waiting_for_acceptance.class);
                       startActivity(Waiting_for_acceptance);
                   }
                   else if (snapshot.getValue().toString().equals("1"))
                   {
                       Intent Home = new Intent(getApplicationContext(), com.example.myapplied.Homepage.class);
                       startActivity(Home);
                   }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }
        else
        {
            Toast.makeText(getApplicationContext(), "الرجاء تأكيد الحساب", Toast.LENGTH_SHORT).show();
        }
    }
}