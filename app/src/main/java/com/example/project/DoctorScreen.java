package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DoctorScreen extends AppCompatActivity {
    TextView nameuser;
    CardView lsp,logout,hm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_screen);
        nameuser = findViewById(R.id.nameuser);
        lsp = findViewById(R.id.patients);
        logout = findViewById(R.id.log_out);
        hm = findViewById(R.id.hm1);

        String name = getIntent().getStringExtra("name");
        nameuser.setText("Welcome Doctor " + name);

        lsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorScreen.this, ListPatient.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorScreen.this, LoginDoctor.class));
            }
        });
        hm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorScreen.this, MainActivity.class));
            }
        });
    }
}