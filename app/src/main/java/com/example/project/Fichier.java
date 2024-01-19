package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Fichier extends AppCompatActivity {
    EditText nom_input,prenom_input,maladie_input;
    Button add,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fichier);

        nom_input = findViewById(R.id.editTextNom);
        prenom_input = findViewById(R.id.editTextPrenom);
        maladie_input = findViewById(R.id.edittextMaladie);
        add = findViewById(R.id.add);
        cancel = findViewById(R.id.cancel);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Fichier.this);
                myDB.addPatient(nom_input.getText().toString().trim(),
                        prenom_input.getText().toString().trim(),
                        maladie_input.getText().toString().trim());
                startActivity(new Intent(Fichier.this, ListPatient.class));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Fichier.this, DoctorScreen.class));
            }
        });
    }
}
