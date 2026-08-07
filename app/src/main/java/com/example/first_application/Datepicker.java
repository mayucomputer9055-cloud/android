package com.example.first_application;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class Datepicker extends AppCompatActivity {
DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_date_picker);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        datePicker = findViewById(R.id.datepicker);

        datePicker.init(
            datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                (vew, year,month,day)->{
                    Snackbar.make(vew,day+"/"+month+"/"+year,Snackbar.LENGTH_LONG).show();
                }
        );
    }
}