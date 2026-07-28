package com.example.first_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Adapter.RecyclerAdapter;
import classes.TableItems;
import mydb.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
TextView username,logout;
EditText ETname, ETemail, ETid;
RecyclerAdapter ra ;


MyDatabase db;
Button BTNSave, BTNUpdate, BTNDelete, BTNClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        db = new MyDatabase(this);

        username = findViewById(R.id.edText);
        logout = findViewById(R.id.txLogout);
        ETemail = findViewById(R.id.edEmail);
        ETname = findViewById(R.id.edName);
        ETid = findViewById(R.id.edId);
        BTNSave = findViewById(R.id.btnSave);
        BTNUpdate = findViewById(R.id.btnUpdate);
        BTNDelete = findViewById(R.id.btnDelete);
        BTNClear = findViewById(R.id.btnclear);

        username.setText(intent.getStringExtra("u_name"));
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);

        RecyclerView rv = findViewById(R.id.tabledata);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ra = new RecyclerAdapter(new ArrayList<>());
        rv.setAdapter(ra);
        loadData();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor ed = sp.edit();
                ed.remove("login");
                ed.remove("u_name");
                ed.apply();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            }
        });

        BTNSave.setOnClickListener(view -> {
            String Sname = ETname.getText().toString();
            String Semail = ETemail.getText().toString();

            boolean inserted = db.insertData(Sname,Semail);
            if(inserted)
            {
                Snackbar.make(view,"record inserted...",Snackbar.LENGTH_LONG).show();
            }
            else {
                Snackbar.make(view,"priblem inserting record...",Snackbar.LENGTH_LONG).show();
            }
            loadData();
            ClearEditText();
        });

        BTNDelete.setOnClickListener(view ->{

            String id = ETid.getText().toString();

           boolean r = db.deleteData(id);

           if(r)
           {
               Snackbar.make(view,"Record id: "+id+" deleted.......",Snackbar.LENGTH_LONG).show();
               ClearEditText();
               loadData();
           }



        });

        BTNUpdate.setOnClickListener(view ->{
            String id,name,email;
            id = ETid.getText().toString();
            name = ETname.getText().toString();
            email = ETemail.getText().toString();

            boolean r = db.updateData(id,name,email);

            if(r)
            {
                Snackbar.make(view,"id  : "+id +" updated...",Snackbar.LENGTH_LONG).show();
                ClearEditText();
                loadData();
            }


        });

        BTNClear.setOnClickListener( view->{
            ClearEditText();
        });


    }

    private void loadData() {
        List<TableItems> l = db.getDataList();
        ra.updateData(l);
    }

    //to clear edittext data
    private void ClearEditText()
    {
        ETemail.setText("");
        ETid.setText("");
        ETname.setText("");
    }
}