package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpAs extends AppCompatActivity {
    private Button button1,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_as);

        button1 = findViewById(R.id.button);
        button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDoctor();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPatient();
            }
        });
    }
    public void openDoctor(){
        Intent intent = new Intent(this, SignUpDoctor.class);
        startActivity(intent);
    }
    public void openPatient(){
        Intent intent = new Intent(this, SignUpPatient.class);
        startActivity(intent);
    }
}
