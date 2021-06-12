package com.example.myapplied.Model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplied.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT=0;
    public static final int MSG_TYPE_Right=1;
    private Context context;
    private List<Chat> chatList;
    private String imageUrl;
    DatabaseReference mRef;
    DatabaseReference mRef1=FirebaseDatabase.getInstance().getReference("Chats");
    DatabaseReference mRef2=FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    FirebaseUser fuser;
    String nameSender;
    public MessageAdapter(Context context ,List<Chat> chatList,String imageUrl)
    {
        this.context=context;
        this.chatList=chatList;
        this.imageUrl=imageUrl;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType ==MSG_TYPE_Right) {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_layout_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }else
        {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_layout_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Chat chat =chatList.get(position);

        fuser= FirebaseAuth.getInstance().getCurrentUser();
        mRef= FirebaseDatabase.getInstance().getReference("Users").child(chat.getSender());
        mRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 Users user = snapshot.getValue(Users.class);
                 nameSender = user.getFirstName() + " " + user.getLastName();
                 if (!chat.getSender().equals(fuser.getUid()))
                     holder.sender_massage.setText(nameSender);
                 if (user.getAccount().equals("طالب")) {
                     holder.profileImage.setImageResource(R.mipmap.stu1);
                     mRef2.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot1) {
                             Users userCur = snapshot1.getValue(Users.class);
                             if (userCur.getAccount().equals("طالب/Admin")) {
                                    holder.imgBtnDeleteAdmin.setVisibility(View.VISIBLE);
                                    holder.imgBtnDeleteAdmin.setOnLongClickListener(new View.OnLongClickListener() {
                                        @Override
                                        public boolean onLongClick(View v) {
                                            Chat chat=chatList.get(position);
                                            mRef1.child(chat.getId()).removeValue();
                                            return false;
                                        }
                                    });
                                 holder.imgBtnDeleteAdmin.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Toast.makeText(context, "انقر نقرة طويلة لحذف الرسالة",Toast.LENGTH_SHORT).show();
                                     }
                                 });
                             }
                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {
                         }
                     });
                 }
                 if (user.getAccount().equals("طالب/Admin")) {
                     holder.profileImage.setImageResource(R.mipmap.stu1);
                 }
                 if (user.getAccount().equals("دكتور/معيد")) {
                     holder.profileImage.setImageResource(R.mipmap.pro);
                     if (chat.getSender().equals(fuser.getUid())) {
                         holder.info_massage.setVisibility(View.VISIBLE);
                         holder.info_massage.setText(chat.getReceiver());
                     }
                     else
                     {
                         holder.sender_massage.setBackgroundColor(Color.parseColor("#FF5BB6FD"));
                     }
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         }) ;
        holder.show_massage.setText(chat.getMassage());
        holder.imgBtnDelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Chat chat=chatList.get(position);
                mRef1.child(chat.getId()).removeValue();
                return false;
            }
        });
        holder.imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "انقر نقرة طويلة لحذف الرسالة",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView show_massage,sender_massage,info_massage;
        public ImageView profileImage;
        public ImageButton imgBtnDelete,imgBtnDeleteAdmin;

        public ViewHolder(View itemView)
        {
            super(itemView);

            imgBtnDelete=itemView.findViewById(R.id.imgBtn_Delete);
            show_massage=itemView.findViewById(R.id.show_massage);
            info_massage=itemView.findViewById(R.id.show_info);
            profileImage=itemView.findViewById(R.id.profile_image);
            sender_massage=itemView.findViewById(R.id.sender_massage);
            imgBtnDeleteAdmin=itemView.findViewById(R.id.imgBtn_DeleteAdmin);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        if (chatList.get(position).getSender().equals(fuser.getUid()))
            return MSG_TYPE_Right;
        else
            return MSG_TYPE_LEFT;
    }
}
