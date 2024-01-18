package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListPatient extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton addbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_patient);
        recyclerView = findViewById(R.id.listpatient);
        addbutton = findViewById(R.id.floatingActionButton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListPatient.this, Fichier.class));
            }
        });
    }
}
