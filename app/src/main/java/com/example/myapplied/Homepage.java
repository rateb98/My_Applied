package com.example.myapplied;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    @Override//22
    protected void onDestroy() {
        super.onDestroy();
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
            Intent intent =new Intent(getApplicationContext(), com.example.myapplied.MainActivity.class);
            this.onDestroy();
            startActivity(intent);

        }
        return true;
    }

    public void btnAcceptance_of_accounts(View view) {
        Intent intent1 = new Intent(getApplicationContext(), com.example.myapplied.acceptance_of_accounts.class);
        startActivity(intent1);
    }
}