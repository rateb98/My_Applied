package com.example.myapplied;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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


public class Waiting_for_acceptance extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_for_acceptance);
        textView=findViewById(R.id.txMessage1);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser CurUser = mAuth.getCurrentUser();
        String userId = CurUser.getUid();
        myRef = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("ready");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equals("-1")) {
                    textView.setText("الرجاء الأنتظار للموافقة على الحساب");
                }
                else if(snapshot.getValue().toString().equals("-2")) {
                    textView.setText("تم رفض حسابك بسبب عدم تحقيقك لشروط القبول");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }




    public void closeApp(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}