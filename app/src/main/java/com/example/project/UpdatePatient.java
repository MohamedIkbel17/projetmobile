package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePatient extends AppCompatActivity {

    EditText nom_input,prenom_input,maladie_input;
    Button update_btn;
    String id, nom, prenom, maladie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);
        nom_input = findViewById(R.id.editTextNom1);
        prenom_input = findViewById(R.id.editTextPrenom1);
        maladie_input = findViewById(R.id.edittextMaladie1);
        update_btn = findViewById(R.id.update);
        getAndSetIntentData();
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdatePatient.this);
                myDB.updatePatientData(id,nom_input.getText().toString(),prenom_input.getText().toString(),maladie_input.getText().toString());
                startActivity(new Intent(UpdatePatient.this, ListPatient.class));
            }
        });

    }
    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nom")&& getIntent().hasExtra("prenom") && getIntent().hasExtra("maladie")){
            // Gettin Data from Intent
            id = getIntent().getStringExtra("id");
            nom = getIntent().getStringExtra("nom");
            prenom = getIntent().getStringExtra("prenom");
            maladie = getIntent().getStringExtra("maladie");
            //Stting Intent Data
            nom_input.setText(nom);
            prenom_input.setText(prenom);
            maladie_input.setText(maladie);
        }else {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
    }
}