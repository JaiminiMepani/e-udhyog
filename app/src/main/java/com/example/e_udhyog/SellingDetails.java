package com.example.e_udhyog;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SellingDetails extends AppCompatActivity {
    EditText sname, s_gstin, sphone, spass, scpass, s_address, s_state, s_pincode;
    String storename, GSTIN_no, phn_num, password, scpassword, address, state, pincode, seller_status = "1", email = "";
    String url = "http://192.168.163.108/eudhyog/s_register.php";
    Button Continue;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;


    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selling_details);



        sname = (EditText) findViewById(R.id.s_storename);
        s_gstin = (EditText) findViewById(R.id.gstin_no);
        sphone = (EditText) findViewById(R.id.s_phone);
        spass = (EditText) findViewById(R.id.s_password);
        scpass = (EditText) findViewById(R.id.s_cpassword);
        s_address = (EditText) findViewById(R.id.s_add);
        s_state = (EditText) findViewById(R.id.s_state);
        s_pincode = (EditText) findViewById(R.id.s_pin);

        Continue = findViewById(R.id.con);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(SellingDetails.this, gso);
        mGoogleSignInClient.signOut();

        //initializing objects
        mAuth = getInstance();

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                storename = sname.getText().toString();
                GSTIN_no = s_gstin.getText().toString();
                phn_num = sphone.getText().toString();
                password = spass.getText().toString();
                scpassword = scpass.getText().toString();
                address = s_address.getText().toString();
                state = s_state.getText().toString();
                pincode = s_pincode.getText().toString();


                if (TextUtils.isEmpty(storename)) {
                    Toast.makeText(SellingDetails.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(GSTIN_no)) {
                    Toast.makeText(SellingDetails.this, "Please Enter Your Store GSTIN Number", Toast.LENGTH_SHORT).show();
                } else if (GSTIN_no.length() == 15) {
                    String regex =  "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z [0-9]{1}$";

                    /* Compile the ReGex
                    Pattern p = Pattern.compile(regex);
                    Matcher m = p.matcher(GSTIN_no);*/
                    Toast.makeText(SellingDetails.this, "please Enter Valid GSTIN number", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(phn_num)) {
                    Toast.makeText(SellingDetails.this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT).show();
                } else if (phn_num.length() < 10) {
                    Toast.makeText(SellingDetails.this, "Please Enter Your 10 Digit Phone Number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SellingDetails.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 8) {
                    Toast.makeText(SellingDetails.this, "please Enter 8 Digit Password", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(scpassword)) {
                    Toast.makeText(SellingDetails.this, "Please Enter Your Confirm Password", Toast.LENGTH_SHORT).show();
                } else if (!scpassword.equals(password)) {
                    Toast.makeText(SellingDetails.this, "Password do not match", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(address)) {
                    Toast.makeText(SellingDetails.this, "Please Enter Your Address and City", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(state)) {
                    Toast.makeText(SellingDetails.this, "Please Enter Your State ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pincode)) {
                    Toast.makeText(SellingDetails.this, "Please Enter Your Pincode ", Toast.LENGTH_SHORT).show();
                } else if (pincode.length() < 6) {
                    Toast.makeText(SellingDetails.this, "please Enter 6 Digit Pincode", Toast.LENGTH_SHORT).show();
                } else {
                    addDataToDatabase(storename, GSTIN_no, phn_num, password, email, address, state, pincode, seller_status);


                  sendVerificationCode();


                }

            }
        });
    }



   private void addDataToDatabase(String storename,String GSTIN_no,String phn_num ,String password,String email,String address,String state,String pincode,String seller_status) {





        // creating a new variable for our request queue
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
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

            }
        } )
        {


            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<>();

                // on below line we are passing our
                // key and value pair to our parameters.
                //params.put("b_id",b_id);
                params.put("storename", storename);
                params.put("GSTIN_no",GSTIN_no);
                params.put("phn_num",phn_num);
                params.put("password", password);
                params.put("email", "");
                params.put("address", address);
                params.put("state",state);
                params.put("pincode",pincode);
                params.put("seller_status", seller_status);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
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
                Toast.makeText(SellingDetails.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Toast.makeText(SellingDetails.this, "otp is successfully sent", Toast.LENGTH_SHORT).show();

                Intent login = new Intent(SellingDetails.this, SellerOtp.class);
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




