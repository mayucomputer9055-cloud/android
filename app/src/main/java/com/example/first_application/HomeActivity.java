package com.example.first_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import mydb.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {
TextView username,logout;
EditText ETname, ETemail;

MyDatabase db;
Button BTNSave;
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

        username = findViewById(R.id.edText);
        logout = findViewById(R.id.txLogout);
        ETemail = findViewById(R.id.edEmail);
        ETname = findViewById(R.id.edName);
        BTNSave = findViewById(R.id.btnSave);

        username.setText(intent.getStringExtra("u_name"));
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);

        db = new MyDatabase(this);
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
        });

    }
}