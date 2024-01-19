package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.project.chahine.MainChahine;
import com.example.project.chahine.MainPatient;

public class PatientScreen extends AppCompatActivity {

    CardView rend,rez,nav,out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_screen);
        rend = findViewById(R.id.rend);
        rez = findViewById(R.id.res);
        nav = findViewById(R.id.navi);
        out = findViewById(R.id.log);

        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientScreen.this, MainPatient.class));
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientScreen.this, MainActivity.class));
            }
        });

    }
}
