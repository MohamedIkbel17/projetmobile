package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListPatient extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton addbutton;
    MyDatabaseHelper myDB;
    ArrayList<String> patient_id,patient_nom,patient_prenom,patient_maladie;
    CustomAdapter customAdapter;
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
        myDB = new MyDatabaseHelper(ListPatient.this);
        patient_id = new ArrayList<>();
        patient_nom = new ArrayList<>();
        patient_prenom = new ArrayList<>();
        patient_maladie = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(ListPatient.this, patient_id,patient_nom,patient_prenom,patient_maladie);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((ListPatient.this)));
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                patient_id.add(cursor.getString(0));
                patient_nom.add(cursor.getString(1));
                patient_prenom.add(cursor.getString(2));
                patient_maladie.add(cursor.getString(3));
            }
        }
    }
}
