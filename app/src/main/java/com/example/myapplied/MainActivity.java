package com.example.myapplied;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplied.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Users user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoLogin();
    }

    public void SingUpActivity(View view) {
        Intent SingUp =new Intent(this, com.example.myapplied.SingUp.class);
        startActivity(SingUp);
    }

    public void LoginActivity(View view) {
        Intent Login =new Intent(this, com.example.myapplied.Login.class);
        startActivity(Login);
    }
    public void AutoLogin()
    {
        user=new Users();
        FirebaseUser CurUser=FirebaseAuth.getInstance().getCurrentUser();
        if(CurUser!=null)
        {
            String userId = CurUser.getUid();
            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users").child(userId);
            if (CurUser.isEmailVerified()) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        user=snapshot.getValue(Users.class);
                        if (user.getReady().equals("0")) {
                            Intent Account_completion = new Intent(getApplicationContext(), com.example.myapplied.Account_completion.class);
                            startActivity(Account_completion);
                        } else if (user.getReady().equals("-1") || snapshot.getValue().toString().equals("-2")) {
                            Intent Waiting_for_acceptance = new Intent(getApplicationContext(), com.example.myapplied.Waiting_for_acceptance.class);
                            startActivity(Waiting_for_acceptance);
                        } else if (user.getReady().equals("1")) {
                            Intent Home = new Intent(getApplicationContext(), com.example.myapplied.Homepage.class);
                            Home.putExtra("username",user.getFirstName()+" "+user.getLastName());
                            Home.putExtra("userId",user.getId());
                            Home.putExtra("account",user.getAccount());
                            startActivity(Home);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }
}