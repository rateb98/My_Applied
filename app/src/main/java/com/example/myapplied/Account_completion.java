package com.example.myapplied;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplied.Model.Users;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Account_completion extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView1,autoCompleteTextView2;
    TextInputLayout textInputLayout1,textInputLayout2;
    EditText edFirstName,edLastName;
    String Account="Stu";
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_completion);
        autoCompleteTextView1 = findViewById(R.id.autoCompleteText);
        autoCompleteTextView2 = findViewById(R.id.autoCompleteText1);
        textInputLayout1 = findViewById(R.id.textInputLayout1);
        textInputLayout2 = findViewById(R.id.textInputLayout2);
        edFirstName=findViewById(R.id.edFirstName);
        edLastName=findViewById(R.id.edLastName);
        Toolbar toolbar=findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        String[] Section = {"تقنيات حاسوب", "ميكاترونكس"};
        String[] Academic_year = {"السنة الأولى", "السنة الثانية", "السنة الثالثة", "السنة الرابعة"};
        //القسم
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, R.layout.caption_item, Section);
        autoCompleteTextView1.setText(arrayAdapter1.getItem(0).toString(), false);
        autoCompleteTextView1.setAdapter(arrayAdapter1);
        //السنوات الدراسية
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.caption_item, Academic_year);
        autoCompleteTextView2.setText(arrayAdapter2.getItem(0).toString(), false);
        autoCompleteTextView2.setAdapter(arrayAdapter2);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_initialization,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id==R.id.itemConvertPro) {
            autoCompleteTextView1.setVisibility(View.GONE);
            autoCompleteTextView2.setVisibility(View.GONE);
            textInputLayout1.setVisibility(View.GONE);
            textInputLayout2.setVisibility(View.GONE);
            Account ="Pro";
        }
        if(id==R.id.itemConvertStu) {
            autoCompleteTextView1.setVisibility(View.VISIBLE);
            autoCompleteTextView2.setVisibility(View.VISIBLE);
            textInputLayout1.setVisibility(View.VISIBLE);
            textInputLayout2.setVisibility(View.VISIBLE);
            Account="Stu";
        }
        return true;
    }

    public void complete(View view) {
        Users user = new Users();
        user.setId(mAuth.getCurrentUser().getUid());
        user.setFirstName(edFirstName.getText().toString());
        user.setLastName(edLastName.getText().toString());

        if (Account.toString().equals("Stu")) {
            user.setSection(autoCompleteTextView1.getText().toString());
            user.setAcademic_year(autoCompleteTextView2.getText().toString());
            user.setAccount("طالب");
        } else {
            user.setSection("def");
            user.setAcademic_year("def");
            user.setAccount("دكتور/معيد");
        }
        user.setReady("-1");
        myRef = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getCurrentUser().getUid());
        myRef.setValue(user);
        Toast.makeText(getApplicationContext(), "تمت العملية بنجاح", Toast.LENGTH_SHORT).show();
    }
}