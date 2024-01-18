package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DoctorScreen extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView nameuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_screen);
        bottomNavigationView = findViewById(R.id.bottomNav);
        nameuser = findViewById(R.id.nameuser);

        String name = getIntent().getStringExtra("name");
        nameuser.setText("Welcome "+name);

        replaceFragment(new FragmentListPatient(),true);
        bottomNavigationView.setOnItemSelectedListener(item ->  {
            int itemId = item.getItemId();

            if (itemId == R.id.list){
                replaceFragment(new FragmentListPatient(),false);
            } else if (itemId == R.id.appoinment){
                replaceFragment(new FragmentListAppointment(),false);
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment, boolean ins){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (ins)
            fragmentTransaction.add(R.id.frameMain,fragment);
        else
            fragmentTransaction.replace(R.id.frameMain,fragment);
        fragmentTransaction.commit();
    }
}