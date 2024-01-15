package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UserScreen extends AppCompatActivity {
    TextView nameuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        nameuser = findViewById(R.id.nameuser);
        String name = getIntent().getStringExtra("name");
        nameuser.setText("Welcome "+name);
    }
}