package com.example.myapplied;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity {

    String userID,account;
    ImageButton btnAcceptance_of_accounts,btnGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        btnAcceptance_of_accounts=findViewById(R.id.imBtnAc);
        btnGroup=findViewById(R.id.imBtnGr);
        Toolbar toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("username"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        userID=getIntent().getStringExtra("userId");
        account=getIntent().getStringExtra("account");
        if(account.equals("طالب/Admin"))
        {
            btnAcceptance_of_accounts.setVisibility(View.VISIBLE);
            btnGroup.setVisibility(View.VISIBLE);
        }
        if(account.equals("طالب"))
        {
            btnGroup.setVisibility(View.VISIBLE);
        }
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

    public void clickAcceptance_of_accounts(View view) {
        Intent intent1 = new Intent(getApplicationContext(), com.example.myapplied.acceptance_of_accounts.class);
        startActivity(intent1);
    }

    public void clickGroup(View view) {
        Intent intent2 =new Intent(this,com.example.myapplied.Group_chat.class);
        startActivity(intent2);
    }
}