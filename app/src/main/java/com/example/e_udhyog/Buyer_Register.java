package com.example.e_udhyog;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Buyer_Register extends AppCompatActivity {

    private EditText etName, etPhone, etPassword, etReenterPassword;
    private Button Verify;
    private String name, phn_num, password, reenterPassword, buyer_status = "1", email = "";
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    GoogleSignInClient mGoogleSignInClient;

    String URL_REGISTER = "http://192.168.163.108/eudhyog/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_register);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(Buyer_Register.this, gso);
        mGoogleSignInClient.signOut();

        //initializing objects
        mAuth = getInstance();

        etName = findViewById(R.id.userName);
        etPhone = findViewById(R.id.phn_num);
        etPassword = findViewById(R.id.b_Password);
        etReenterPassword = findViewById(R.id.b_confirmPassword);
        Verify = (Button) findViewById(R.id.verify);

        Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                phn_num = etPhone.getText().toString();
                password = etPassword.getText().toString();
                reenterPassword = etReenterPassword.getText().toString();

                if (name.isEmpty() || phn_num.isEmpty() || password.isEmpty() || reenterPassword.isEmpty()) {
                    Toast.makeText(Buyer_Register.this, "please enter valid data", Toast.LENGTH_SHORT).show();
                } else if (phn_num.length() < 10) {
                    Toast.makeText(Buyer_Register.this, "Please Enter Your 10 Digit Phone Number", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 8) {
                    Toast.makeText(Buyer_Register.this, "please Enter 8 Digit Password", Toast.LENGTH_SHORT).show();
                } else if (!reenterPassword.equals(password)) {
                    Toast.makeText(Buyer_Register.this, "Password do not match", Toast.LENGTH_SHORT).show();
                } else {


                    Register(name, phn_num, password, email, buyer_status);
                    sendVerificationCode();

                }
            }
        });
    }

    private void Register(String name, String phn_num, String password, String email, String buyer_status) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("anyText", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();


                                Intent login = new Intent(Buyer_Register.this, Otp.class);
                                // login.putExtra("phn_num", phn_num);
                                startActivity(login);


                            } else if (success.equals("0")) {

                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Registration Error !2" + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("name", name);
                params.put("phn_num", phn_num);
                params.put("password", password);
                params.put("email", "");
                params.put("buyer_status", buyer_status);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void sendVerificationCode() {


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(Buyer_Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Toast.makeText(Buyer_Register.this, "otp is successfully sent", Toast.LENGTH_SHORT).show();

                Intent login = new Intent(Buyer_Register.this, Otp.class);
                login.putExtra("phn_num", phn_num);
                login.putExtra("s", s);
                startActivity(login);


            }

        };
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthOptions options= PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber("+91" + phn_num)
                .setTimeout( 60L,TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(  mCallbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}