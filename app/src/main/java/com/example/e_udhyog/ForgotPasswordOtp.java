package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class ForgotPasswordOtp extends AppCompatActivity {

    String s, phn_num;
    private EditText et1, et2, et3, et4, et5, et6;
    Button Verify;
        @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.forgotpasswordotp);



            et1 = findViewById(R.id.c1);
            et2 = findViewById(R.id.c2);
            et3 = findViewById(R.id.c3);
            et4 = findViewById(R.id.c4);
            et5 = findViewById(R.id.c5);
            et6 = findViewById(R.id.c6);


            phn_num = getIntent().getStringExtra("phn_num");
            s = getIntent().getStringExtra("s");
             Verify =findViewById(R.id.verify);
            editTextInput();

            Verify .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (et1.getText().toString().trim().isEmpty() ||
                            et2.getText().toString().trim().isEmpty() ||
                            et3.getText().toString().trim().isEmpty() ||
                            et4.getText().toString().trim().isEmpty() ||
                            et5.getText().toString().trim().isEmpty() ||
                            et6.getText().toString().trim().isEmpty()) {
                        Toast.makeText(ForgotPasswordOtp.this, "OTP is not valid ", Toast.LENGTH_SHORT).show();
                    } else {
                        if (s != null) {
                            String code = et1.getText().toString().trim() + et2.getText().toString().trim() + et3.getText().toString().trim() +
                                    et4.getText().toString().trim() + et5.getText().toString().trim() + et6.getText().toString().trim();


                            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(s, code);
                            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                            firebaseAuth.signInWithCredential(credential)
                                    .addOnCompleteListener(ForgotPasswordOtp.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {

                                                Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
                                                intent.putExtra("phn_num",phn_num);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                System.out.println("fvfdgdf" +  phn_num);
                                            } else {
                                                Toast.makeText(ForgotPasswordOtp.this, "OTP is not valid", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });
                        }
                    }
                }

            });
        }

    private void editTextInput() {
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                et2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence charSequence,int i, int i1, int i2){

            }

            @Override
            public void onTextChanged (CharSequence charSequence,int i, int i1, int i2){
                et3.requestFocus();
            }

            @Override
            public void afterTextChanged (Editable editable){

            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence charSequence,int i, int i1, int i2){

            }

            @Override
            public void onTextChanged (CharSequence charSequence,int i, int i1, int i2){
                et4.requestFocus();
            }

            @Override
            public void afterTextChanged (Editable editable){

            }
        });


        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence charSequence,int i, int i1, int i2){

            }

            @Override
            public void onTextChanged (CharSequence charSequence,int i, int i1, int i2){
                et5.requestFocus();
            }

            @Override
            public void afterTextChanged (Editable editable){

            }
        });



        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                et6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
