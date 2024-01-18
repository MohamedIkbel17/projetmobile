package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginDoctor extends AppCompatActivity {
    /*ActivityMainBinding binding;
    UserDatabase myDb;
    UserDao userDao;*/
    EditText email1, password1;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_doctor);

        email1 = findViewById(R.id.inputEmail2);
        password1 = findViewById(R.id.inputpassword2);
        login = findViewById(R.id.btnlogin2);
        TextView btn = findViewById(R.id.sign2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginDoctor.this, SignUpAs.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailText = email1.getText().toString();
                final String passwordText = password1.getText().toString();
                if (emailText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fill all Fields!", Toast.LENGTH_SHORT).show();
                }else {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(emailText, passwordText,0);
                            if (userEntity == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),"Invalid Doctor",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else {
                                String name = userEntity.name;
                                startActivity(new Intent(LoginDoctor.this, DoctorScreen.class)
                                        .putExtra("name",name));
                            }
                        }
                    }).start();
                }
            }
        });

        /*binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        myDb= Room.databaseBuilder(this,UserDatabase.class,"users")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        userDao=myDb.userDao();

        binding.btnlogin2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String email=binding.inputEmail2.getText().toString();
                String password=binding.inputpassword2.getText().toString();

                Log.d("LoginAttempt", "Email: " + email + ", Password: " + password);

                if (userDao.login(email,password)){
                    startActivity(new Intent(MainActivity.this,Connect.class));
                }
                else {
                    Toast.makeText(MainActivity.this,"Invalid Teeba Laabed", Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }
}