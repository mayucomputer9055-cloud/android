package com.example.first_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
EditText ed_passwd, ed_email;
AppCompatButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ed_passwd = findViewById(R.id.ed_passwd);
        ed_email = findViewById(R.id.ed_email);
        login = findViewById(R.id.submit);

        //shared prefrence to keep the user login data
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        Intent intent = new Intent(MainActivity.this, HomeActivity.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ed_email.getText().toString();
                String pass = ed_passwd.getText().toString();
                if(email.equals("admin") && pass.equals("1234"))
                {
                    Snackbar.make(view,"Admin login",Snackbar.LENGTH_LONG).show();
                    editor.putBoolean("login",true);
                    editor.putString("u_name",email);
                    editor.apply();
                    intent.putExtra("u_name",email);
                    startActivity(intent);
                    finish();
                }
                else{
                    Snackbar.make(view,"Other login",Snackbar.LENGTH_LONG).show();
                    editor.putString("u_name",email);
                    editor.putBoolean("login",true);
                    editor.apply();
                    intent.putExtra("u_name",email);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}