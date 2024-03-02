package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SellerChangePassword extends AppCompatActivity {

    EditText etpassword,ccpass;
        String url = "http://192.168.163.108/eudhyog/sellerforgotpassword.php";
        String phn_num, password;
        Button Save;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellerchnage_password);

        etpassword= (EditText) findViewById(R.id.change_Password);
        ccpass = (EditText) findViewById(R.id.change_confirmPassword);

        phn_num = getIntent().getStringExtra("phn_num");
        Save =(Button)findViewById(R.id.change_save);
        Save.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        password = etpassword.getText().toString();
        String ccpassword = ccpass.getText().toString();

        if (TextUtils.isEmpty(password)) {
        Toast.makeText(SellerChangePassword.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 8) {
        Toast.makeText(SellerChangePassword.this, "please Enter 8 Digit Password", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ccpassword)) {
        Toast.makeText(SellerChangePassword.this, "Please Enter Your Confirm Password", Toast.LENGTH_SHORT).show();
        } else if (!ccpassword.equals(password)) {
        Toast.makeText(SellerChangePassword.this, "Password do not match", Toast.LENGTH_SHORT).show();
        } else {
        Toast.makeText(SellerChangePassword.this, "Password Is Successfully Changed", Toast.LENGTH_SHORT).show();
        }
        change();

        }
        });
        }



private void change()
        {

final StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
        new Response.Listener<String>() {
@Override
public void onResponse(String response) {

        Log.e("anyText",response);
        try{
        JSONObject jsonObject = new JSONObject(response);
        String success = jsonObject.getString("success");
        String message = jsonObject.getString("message");




        if(success.equals("1")){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
        Intent i = new Intent(SellerChangePassword.this,SellerMain.class);
        startActivity(i);
        }
        if(success.equals("0")){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

        }

        } catch (Exception e) {
        e.printStackTrace();

        }
        }
        }, new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError error) {

        Toast.makeText(getApplicationContext(),"login Error !2"+error,Toast.LENGTH_LONG).show();
        }
        })
        {
@Override
protected Map<String, String> getParams() {
        Map<String,String> params = new HashMap<>();

        params.put("phn_num",phn_num);
        params.put("password",password);


        return params;
        }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        }
        }
