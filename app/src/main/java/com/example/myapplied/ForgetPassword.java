package com.example.myapplied;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    EditText edEmail;
    Button btnSendEmail;

    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Toolbar toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("تغيير كلمة المرور");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        edEmail=findViewById(R.id.edEmailForget);
        btnSendEmail=findViewById(R.id.btnForgetPassword);

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=edEmail.getText().toString();

                if(email.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"يجب أن تضع بريدك في البداية..",Toast.LENGTH_LONG).show();
                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"تم إرسال رسالة إلى بريدك لإعادة تعيين كلمة مرور جديدة",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),Login.class));
                                finish();
                            }
                            else
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }
                    });
                }

            }
        });
    }
}