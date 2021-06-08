package com.example.myapplied;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplied.Model.Chat;
import com.example.myapplied.Model.MessageAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pro_Massage extends AppCompatActivity {


    ImageButton image_send;
    EditText text_send;
    String userId,groupInfo;

    MessageAdapter messageAdapter;
    List<Chat> chatList;

    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro__massage);
        text_send=findViewById(R.id.text_send);
        image_send=findViewById(R.id.btn_send);
        relativeLayout=findViewById(R.id.bottom);
        userId= FirebaseAuth.getInstance().getUid();

        Toolbar toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("إرسال رسالة ( يرجى تحديد... )");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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
                else
                    Toast.makeText(getApplicationContext(), "لا يمكنك إرسال رسالة فارغة او عدم تحديد الدفعة المرسل إليه", Toast.LENGTH_SHORT).show();
                text_send.setText("");
            }
        });
        readMessage(userId);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_section_year,menu);
        return true;
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        relativeLayout.setVisibility(View.VISIBLE);
        int id =item.getItemId();
        if(id==R.id.secTecYe1)
        {
            text_send.setHint("رسالة إلى دفعة تقنيات الحاسوب السنة الأولى..");
            groupInfo="تقنيات حاسوب/السنة الأولى";
        }
        if(id==R.id.secTecYe2)
        {
            text_send.setHint("رسالة إلى دفعة تقنيات الحاسوب السنة الثانية..");
            groupInfo="تقنيات حاسوب/السنة الثانية";
        }
        if(id==R.id.secTecYe3)
        {
            text_send.setHint("رسالة إلى دفعة تقنيات الحاسوب السنة الثالثة..");
            groupInfo="تقنيات حاسوب/السنة الثالثة";
        }
        if(id==R.id.secTecYe4)
        {
            text_send.setHint("رسالة إلى دفعة تقنيات الحاسوب السنة الرابعة..");
            groupInfo="تقنيات حاسوب/السنة الرابعة";
        }
        if(id==R.id.secMecYe1)
        {
            text_send.setHint("رسالة إلى دفعة ميكاترونكس السنة الأولى..");
            groupInfo="ميكاترونكس/السنة الأولى";
        }
        if(id==R.id.secMecYe2)
        {
            text_send.setHint("رسالة إلى دفعة ميكاترونكس السنة الثانية..");
            groupInfo="ميكاترونكس/السنة الثانية";
        }
        if(id==R.id.secMecYe3)
        {
            text_send.setHint("رسالة إلى دفعة ميكاترونكس السنة الثالثة..");
            groupInfo="ميكاترونكس/السنة الثالثة";
        }
        if(id==R.id.secMecYe4)
        {
            text_send.setHint("رسالة إلى دفعة ميكاترونكس السنة الرابعة..");
            groupInfo="ميكاترونكس/السنة الرابعة";
        }
        return false;

    }
    private void readMessage(final String myId)
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
                    if (chat.getSender().equals(myId))
                        chatList.add(chat);
                }

                messageAdapter=new MessageAdapter(Pro_Massage.this,chatList,"");
                recyclerView.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}