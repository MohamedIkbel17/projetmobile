package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdatePatient extends AppCompatActivity {
    EditText nom_input,prenom_input,maladie_input;
    Button update_btn;
    String id, nom, prenom, maladie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_patient);

        nom_input = findViewById(R.id.editTextNom1);
        prenom_input = findViewById(R.id.editTextPrenom1);
        maladie_input = findViewById(R.id.edittextMaladie1);
        update_btn = findViewById(R.id.update);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getAndSetIntentData();
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
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}
