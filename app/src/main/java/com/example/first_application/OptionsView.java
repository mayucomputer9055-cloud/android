package com.example.first_application;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OptionsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_options_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lv = findViewById(R.id.list_view);
        String[] options = {"database", "DatePicker","DatepickerDialouge"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,options);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemtext = adapterView.getItemAtPosition(i).toString();
                Snackbar.make(view,itemtext,Snackbar.LENGTH_LONG).show();
                Intent intent;
                switch (itemtext)
                {
                    case "database":
                    {
                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("u_name",intent.getStringExtra("u_name"));
                        startActivity(intent);
                        break;
                    }
                    case "DatePicker":
                    {
                        startActivity(new Intent(OptionsView.this, Datepicker.class));
                        break;
                    }
                    case "DatepickerDialouge":
                    {
                        Calendar cal = Calendar.getInstance();
                        int year = cal.get(Calendar.YEAR), month = cal.get(Calendar.MONTH), day = cal.get(Calendar.DAY_OF_MONTH);
                        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                Snackbar.make(view,)
                            }
                        },year,month,day).show();
                        break;
                    }
                }
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(OptionsView.this)
                        .setMessage("Alert Dialog")
                        .setTitle("Alert")
                        .setPositiveButton("yes", (dialog,which)->{
                            finish();
                        })
                        .setNegativeButton("No", (dialog, which)->{
                            dialog.dismiss();
                        })
                        .create()
                        .show();
            }
        });
    }
}