package com.example.myapplied;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class schedule_view extends AppCompatActivity {
    ImageView scheduleView;
    ProgressBar progressBar;
    TextView textView;
    StorageReference sRef=FirebaseStorage.getInstance().getReference();
    StorageReference Ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_view);
        Toolbar toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("برنامج الأسبوع");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        scheduleView=findViewById(R.id.scheduleView);
        progressBar=findViewById(R.id.progressBar2);
        textView=findViewById(R.id.textView);
        Ref=sRef.child(getIntent().getStringExtra("info"));

        try {
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            final File localFile = File.createTempFile("Temp","jpeg");
            Ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    scheduleView.setImageBitmap(bitmap);
                    progressBar.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "حدث خطاء أثناء التحميل أو لم يتم رفع برنامج الأسبوع", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            });


        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

}