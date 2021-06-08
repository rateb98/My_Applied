package com.example.myapplied;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplied.Model.UserAdapter;
import com.example.myapplied.Model.Users;
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
        getSupportActionBar().setTitle("الموافقة على الحسابات");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
   protected void onStart() {
        super.onStart();
        myRef = FirebaseDatabase.getInstance().getReference("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot u:snapshot.getChildren())
                {
                    Users user = u.getValue(Users.class);
                    if(user.getReady().equals("-1"))
                    UserList.add(user);
                }
                Toast.makeText(getApplicationContext(), UserList.toString(),Toast.LENGTH_SHORT).show();
                userAdapter=new UserAdapter(getApplicationContext(),UserList);
                recyclerView.setAdapter(userAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
