package com.example.myapplied;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplied.Model.Chat;
import com.example.myapplied.Model.MessageAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Group_chat extends AppCompatActivity {

    ImageButton image_send;
    EditText text_send;
    String userId,groupInfo;
    MessageAdapter messageAdapter;
    List<Chat> chatList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        userId=getIntent().getStringExtra("userId");
        groupInfo=getIntent().getStringExtra("groupIfo");
        Toolbar toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(groupInfo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        image_send=findViewById(R.id.btn_send);
        text_send=findViewById(R.id.text_send);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        image_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg =text_send.getText().toString();
                if(!msg.equals(""))
                {
                    sendMassage(userId,groupInfo,msg);
                }
                else {
                    Toast.makeText(getApplicationContext(), "لا يمكنك إرسال رسالة فارغة", Toast.LENGTH_SHORT).show();
                }
                text_send.setText("");
            }
        });
        readMessage(userId,groupInfo,"");
    }

    private void sendMassage(String sender,String receiver,String massage)
    {
        DatabaseReference mRef= FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> hashMap=new HashMap<>();
        String id=mRef.push().getKey();
        hashMap.put("id",id);
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("massage",massage);
        mRef.child("Chats").child(id).setValue(hashMap);
    }

    private void readMessage(final String myId,final String groupInfo,final String imageUrl)
    {
        chatList=new ArrayList<>();
        DatabaseReference mRef= FirebaseDatabase.getInstance().getReference("Chats");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                Chat chat=dataSnapshot.getValue(Chat.class);
                if (chat.getReceiver().equals(groupInfo))
                    chatList.add(chat);
                }

                messageAdapter=new MessageAdapter(Group_chat.this,chatList,imageUrl);
                recyclerView.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}