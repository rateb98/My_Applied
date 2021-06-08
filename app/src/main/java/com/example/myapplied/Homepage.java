package com.example.myapplied;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplied.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Homepage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    Users curUser;
    ImageButton btnAcceptance_of_accounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        mAuth=FirebaseAuth.getInstance();
        btnAcceptance_of_accounts=findViewById(R.id.imBtnAc);
        curUser=new Users();
        curUser.setId(mAuth.getCurrentUser().getUid());
        myRef = FirebaseDatabase.getInstance().getReference("Users").child(curUser.getId());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                curUser=snapshot.getValue(Users.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
        Toolbar toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("username"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id==R.id.itemLogout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Homepage.this,MainActivity.class));
            finish();
        }
        return false;
    }

    public void btnAcceptance_of_accounts(View view) {
        Intent intent1 = new Intent(getApplicationContext(), com.example.myapplied.acceptance_of_accounts.class);
        startActivity(intent1);
    }

    public void btnGroup(View view) {
        Intent intent =new Intent(this,com.example.myapplied.Group_chat.class);
        startActivity(intent);
        Toast.makeText(this,curUser.getFirstName(),Toast.LENGTH_LONG).show();
    }
}