package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordOtp extends AppCompatActivity {
        @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.forgotpasswordotp);
            Button Verify =findViewById(R.id.verify);
            Verify .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ForgotPasswordOtp.this,ChangePassword.class);
                    startActivity(intent);}
            });
        }
}
