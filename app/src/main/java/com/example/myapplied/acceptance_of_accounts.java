package com.example.myapplied;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplied.Model.UserAdapter;
import com.example.myapplied.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class acceptance_of_accounts extends AppCompatActivity {

    private List<Users> UserList ;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private DatabaseReference myRef;
    int itemSelect=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptance_of_accounts);
        recyclerView=findViewById(R.id.list_user);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        UserList =new ArrayList<>();
        Toolbar toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("الحسابات المنتظرة");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    @Override
    protected void onStart() {
        super.onStart();
        viewUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.itemWaiting) {
            itemSelect = 0;
            viewUser();
            Toolbar toolbar = findViewById(R.id.tool_Bar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(" الحسابات المنتظرة");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        }
        if (id == R.id.itemAccepted) {
            itemSelect = 1;
            viewUser();

            Toolbar toolbar = findViewById(R.id.tool_Bar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(" الحسابات المقبولة");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        }
        if (id == R.id.itemRejected) {
            itemSelect = 2;
            viewUser();
            Toolbar toolbar = findViewById(R.id.tool_Bar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(" الحسابات المرفوضة");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        return false;
    }

    public void viewUser()
    {
        myRef = FirebaseDatabase.getInstance().getReference("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserList.clear();
                for (DataSnapshot u:snapshot.getChildren()) {
                    Users user = u.getValue(Users.class);
                    if(itemSelect==0&&!user.getId().equals(FirebaseAuth.getInstance().getUid())&&user.getReady().equals("-1"))
                        UserList.add(user);
                    if(itemSelect==1&&!user.getId().equals(FirebaseAuth.getInstance().getUid())&&user.getReady().equals("1"))
                        UserList.add(user);
                    if(itemSelect==2&&!user.getId().equals(FirebaseAuth.getInstance().getUid())&&user.getReady().equals("-2"))
                        UserList.add(user);

                }
                userAdapter=new UserAdapter(getApplicationContext(),UserList);
                recyclerView.setAdapter(userAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
