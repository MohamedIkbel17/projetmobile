package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpDoctor extends AppCompatActivity {

    EditText email,password,name;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor);

        email=findViewById(R.id.inputEmail12);
        password=findViewById(R.id.inputpassword12);
        name=findViewById(R.id.name12);
        register=findViewById(R.id.btnlogin12);
        TextView btn = findViewById(R.id.textView);
        TextView btn1 = findViewById(R.id.GoLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpDoctor.this, SignUpAs.class));
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpDoctor.this,MainActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create User Entity
                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(email.getText().toString());
                userEntity.setPassword(password.getText().toString());
                userEntity.setName(name.getText().toString());
                userEntity.setType(0);
                if (validateInput(userEntity)){
                    //Do insert operation
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Register
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Doctor Registered", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();

                }else{
                    Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateInput(UserEntity userEntity){

        if(userEntity.getName().isEmpty() ||
                userEntity.getPassword().isEmpty() ||
                userEntity.getName().isEmpty()
        ){
            return false;
        }
            return true;
    }
}