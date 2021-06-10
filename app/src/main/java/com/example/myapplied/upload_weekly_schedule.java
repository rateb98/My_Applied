package com.example.myapplied;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class upload_weekly_schedule extends AppCompatActivity {
    Button b1, b2, b3, b4, b5, b6, b7, b8;
    StorageReference sRef = null;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_weekly_schedule);
        progressBar=findViewById(R.id.progressBar);
        sRef = FirebaseStorage.getInstance().getReference();
        Toolbar toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("إعداد برامج الأسبوع");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
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
                startActivityForResult(intentImg, 1);

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
                startActivityForResult(intentImg, 3);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 4);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 5);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 6);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 7);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImg = new Intent(Intent.ACTION_PICK);
                intentImg.setType("image/*");
                startActivityForResult(intentImg, 8);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        StorageReference storageReference;
        if (requestCode == 1 && resultCode == RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            En(true);
            Uri uri = data.getData();
            storageReference = sRef.child("تقنيات حاسوب السنة الأولى");
            if (uri != null) {
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "تمت العملية بنجاح", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        En(false);
                    }
                });
            }
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            En(true);
            Uri uri = data.getData();
            storageReference = sRef.child("تقنيات حاسوب السنة الثانية");
            if (uri != null) {
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "تمت العملية بنجاح", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        En(false);
                    }
                });
            }
        }
        if (requestCode == 3 && resultCode == RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            En(true);
            Uri uri = data.getData();
            storageReference = sRef.child("تقنيات حاسوب السنة الثالثة");
            if (uri != null) {
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "تمت العملية بنجاح", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        En(false);
                    }
                });
            }
        }
        if (requestCode == 4 && resultCode == RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            En(true);
            Uri uri = data.getData();
            storageReference = sRef.child("تقنيات حاسوب السنة الرابعة");
            if (uri != null) {
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "تمت العملية بنجاح", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        En(false);
                    }
                });
            }
        }
        if (requestCode == 5 && resultCode == RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            En(true);
            Uri uri = data.getData();
            storageReference = sRef.child("ميكاترونكس السنة الأولى");
            if (uri != null) {
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "تمت العملية بنجاح", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        En(false);
                    }
                });
            }
        }
        if (requestCode == 6 && resultCode == RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            En(true);
            Uri uri = data.getData();
            storageReference = sRef.child("ميكاترونكس السنة الثانية");
            if (uri != null) {
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "تمت العملية بنجاح", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        En(false);
                    }
                });
            }
        }
        if (requestCode == 7 && resultCode == RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            En(true);
            Uri uri = data.getData();
            storageReference = sRef.child("ميكاترونكس السنة الثالثة");
            if (uri != null) {
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "تمت العملية بنجاح", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        En(false);
                    }
                });
            }
        }
        if (requestCode == 8 && resultCode == RESULT_OK) {
            progressBar.setVisibility(View.VISIBLE);
            En(true);
            Uri uri = data.getData();
            storageReference = sRef.child("ميكاترونكس السنة الرابعة");
            if (uri != null) {
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "تمت العملية بنجاح", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                        En(false);
                    }
                });
            }
        }

    }
    private void En(boolean b)
    {
        if(b==true)
        {
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(false);
            b5.setEnabled(false);
            b6.setEnabled(false);
            b7.setEnabled(false);
            b8.setEnabled(false);
        }
        if(b==false)
        {
            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(true);
            b4.setEnabled(true);
            b5.setEnabled(true);
            b6.setEnabled(true);
            b7.setEnabled(true);
            b8.setEnabled(true);
        }
    }
}
