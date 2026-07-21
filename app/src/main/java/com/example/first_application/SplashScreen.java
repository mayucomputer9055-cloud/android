package com.example.first_application;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        Intent i = new Intent(SplashScreen.this,MainActivity.class);
        SharedPreferences sp = getSharedPreferences("user",MODE_PRIVATE);
        boolean login = sp.getBoolean("login",false);
        String uname = sp.getString("u_name","none");

        new Handler().postDelayed(() -> {
            Intent i;
            if (login)
            {
                i = new Intent(SplashScreen.this, HomeActivity.class);
                i.putExtra("u_name",uname);
                startActivity(i);
            }
            else {
                i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
            }
            finish();
        },3000);
    }
}