package com.example.myapplied;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class upload_weekly_schedule extends AppCompatActivity {
    Button b1, b2, b3, b4, b5, b6, b7, b8;
    StorageReference sRef = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_weekly_schedule);
        sRef = FirebaseStorage.getInstance().getReference();

        b1 = findViewById(R.id.btnUpYea1Tec);
        b2 = findViewById(R.id.btnUpYea2Tec);
        b3 = findViewById(R.id.btnUpYea3Tec);
        b4 = findViewById(R.id.btnUpYea4Tec);
        b5 = findViewById(R.id.btnUpYea1Mec);
        b6 = findViewById(R.id.btnUpYea2Mec);
        b7 = findViewById(R.id.btnUpYea3Mec);
        b8 = findViewById(R.id.btnUpYea4Mec);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 2);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 2);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 2);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 2);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 2);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 2);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        StorageReference storageReference;
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            storageReference = sRef.child("تقنيات حاسوب السنة الأولى");
            if (uri != null) {
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "تمت العملية بنجاح", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
}
