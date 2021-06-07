package com.example.myapplied.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplied.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<Users> usersList;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    public UserAdapter(Context context ,List<Users> usersList)
    {
        this.context=context;
        this.usersList=usersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Users user=usersList.get(position);
        holder.txMessage.setVisibility(View.GONE);
        holder.userName.setText(user.getFirstName()+" "+user.getLastName());

        if(user.getAccount().equals("طالب"))
        {
            holder.profileImage.setImageResource(R.mipmap.stu1);
            holder.secYeName.setText(user.getSection()+"/"+user.getAcademic_year());
        }
        else if (user.getAccount().equals("دكتور/معيد"))
        {
            holder.secYeName.setVisibility(View.GONE);
            holder.profileImage.setImageResource(R.mipmap.pro);
        }

        holder.btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users upUser = usersList.get(position);
                upUser.setReady("1");
                myRef = FirebaseDatabase.getInstance().getReference("Users").child(upUser.getId());
                myRef.setValue(upUser);
                holder.btnAc.setVisibility(View.GONE);
                holder.btnBl.setVisibility(View.GONE);
                holder.txMessage.setVisibility(View.VISIBLE);
                holder.txMessage.setText("تم قبول الحساب");
            }
        });
        holder.btnBl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users upUser = usersList.get(position);
                upUser.setReady("-2");
                myRef = FirebaseDatabase.getInstance().getReference("Users").child(upUser.getId());
                myRef.setValue(upUser);
                holder.btnAc.setVisibility(View.GONE);
                holder.btnBl.setVisibility(View.GONE);
                holder.txMessage.setVisibility(View.VISIBLE);
                holder.txMessage.setText("تم رفض الحساب");
            }
        });
    }
    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userName,secYeName,txMessage;
        public ImageView profileImage;
        public Button btnAc,btnBl;


        public ViewHolder(View itemView)
        {
            super(itemView);
            userName=itemView.findViewById(R.id.txFullName);
            profileImage=itemView.findViewById(R.id.profileImage);
            secYeName =itemView.findViewById(R.id.txSecYe);
            btnAc=itemView.findViewById(R.id.btnAc);
            btnBl=itemView.findViewById(R.id.btnBl);
            txMessage=itemView.findViewById(R.id.txMessage);
        }
    }
}

