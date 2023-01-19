package com.example.e_udhyog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginRegisterMain extends AppCompatActivity {

    EditText nameRegister;
    EditText phoneRegister;
    EditText passwordRegister;
    Button register;

    EditText phoneLogin;
    EditText passwordLogin;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_register);
        setContentView(R.layout.buyer_login);
        nameRegister = findViewById(R.id.userName);
        phoneRegister = findViewById(R.id.phn_num);
        passwordRegister = findViewById(R.id.Password);
        register = findViewById(R.id.verify);

        phoneLogin = findViewById(R.id.phnum);
        passwordLogin = findViewById(R.id.password_login);
        login = findViewById(R.id.loginbtn);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameRegister.getText().toString();
                String phone = phoneRegister.getText().toString();
                String password = passwordRegister.getText().toString();
                String url = "http://127.0.0.1/eudhyog/buyer_register/insertData.php";
                String type = "register";
                BackgroundWorker backgroundWorker = new BackgroundWorker(LoginRegisterMain.this);
                backgroundWorker.execute(url,type,name,phone,password);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameRegister.getText().toString();
                String phone = phoneLogin.getText().toString();
                String password = passwordLogin.getText().toString();
                String url = "http://127.0.0.1/eudhyog/buyer_login/validateData.php";
                String type = "login";
                BackgroundWorker backgroundWorker = new BackgroundWorker(LoginRegisterMain.this);
                backgroundWorker.execute(url,type,name,phone,password);
            }
        });

    }
}