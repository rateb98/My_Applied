package com.example.myapplied;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Group_chat extends AppCompatActivity {

    ImageButton image_send;
    EditText text_send;
    String userId,groupInfo;
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
    }

    private void sendMassage(String sender,String receiver,String massage)
    {
        DatabaseReference mRef= FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("massage",massage);
        mRef.child("Chats").push().setValue(hashMap);
    }
}