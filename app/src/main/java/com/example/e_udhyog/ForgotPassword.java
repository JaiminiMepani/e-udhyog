package com.example.e_udhyog;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ForgotPassword extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    GoogleSignInClient mGoogleSignInClient;
    String phn_num;
    EditText etPhone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        etPhone = findViewById(R.id.phn1);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(ForgotPassword.this, gso);
        mGoogleSignInClient.signOut();

        //initializing objects
        mAuth = getInstance();


        Button Next =findViewById(R.id.next);
        Next .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();

            }
        });
    }
    private void sendVerificationCode() {

        phn_num = etPhone.getText().toString();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(ForgotPassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Toast.makeText(ForgotPassword.this, "otp is successfully sent", Toast.LENGTH_SHORT).show();

                Intent login = new Intent(ForgotPassword.this, ForgotPasswordOtp.class);
                login.putExtra("phn_num", phn_num);
                login.putExtra("s", s);
                startActivity(login);

            }

        };
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthOptions options= PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber("+91" + phn_num)
                .setTimeout( 60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(  mCallbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}
