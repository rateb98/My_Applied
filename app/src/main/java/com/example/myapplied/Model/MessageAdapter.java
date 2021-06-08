package com.example.myapplied.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplied.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT=0;
    public static final int MSG_TYPE_Right=1;
    private Context context;
    private List<Chat> chatList;
    private String imageUrl;

    FirebaseUser fuser;
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
        holder.show_massage.setText(chat.getMassage());
        holder.profileImage.setImageResource(R.mipmap.stu1);
    }
    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView show_massage;
        public ImageView profileImage;

        public ViewHolder(View itemView)
        {
            super(itemView);
            show_massage=itemView.findViewById(R.id.show_massage);
            profileImage=itemView.findViewById(R.id.profile_image);

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
