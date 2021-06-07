package com.example.myapplied;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity {

    ImageButton btnAcceptance_of_accounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnAcceptance_of_accounts=findViewById(R.id.imBtnAc);
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
}