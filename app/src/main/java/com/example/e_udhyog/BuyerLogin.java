package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class BuyerLogin extends AppCompatActivity {


    String URL_LOGIN = "http://192.168.163.108/eudhyog/b_login.php";
     EditText etPhone, etPassword;
     Button b_login;
     String phn_num, password;
    TextView ForgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_login);

    


        etPhone = findViewById(R.id.phn_num);
        etPassword = findViewById(R.id.password);

     ForgotPassword = findViewById(R.id.forgotpassword);
         b_login = findViewById(R.id.b_loginbtn);


        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BuyerLogin.this,ForgotPassword.class);
                startActivity(i);
            }
        });

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               phn_num = etPhone.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                Intent i = new Intent(BuyerLogin.this,BuyerMain.class);
                startActivity(i);
               if(phn_num.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(BuyerLogin.this,"please enter valid data",Toast.LENGTH_SHORT).show();
                }else {


                    Login();
                }
            }
        });
    }


    private void Login()
    {

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("anyText",response);
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            String name = jsonObject.getString("name");
                            String phn_num = jsonObject.getString("phn_num");

                            if(success.equals("1")){
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                Intent i = new Intent(BuyerLogin.this,BuyerMain.class);
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


/*public class BuyerLogin extends AppCompatActivity {


    private EditText etPhone, etPassword;
    private Button b_login;
    private String phn_num, password;
    String url = "http://192.168.8.108/eudhyog/b_login.php";
    SharedPreferences.Editor preferencesEditor;

    private static final String TAG = BuyerLogin.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_login);


        etPhone = findViewById(R.id.phn_num);
        etPassword = findViewById(R.id.password);

        TextView Forgotpassword = findViewById(R.id.forgotpassword);
        Button b_login = findViewById(R.id.b_loginbtn);
        Forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerLogin.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

            }

        });

    }


    public void login() {
        phn_num = etPhone.getText().toString().trim();
        password = etPassword.getText().toString().trim();


        if (!phn_num.equals("") && !password.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("anyText", response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        String message = jsonObject.getString("message");

                        if ( success.equals("1") ) {
                            Toast.makeText(getApplicationContext(), "Logged In  Success", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(BuyerLogin.this, BuyerMain.class);
                            startActivity(i);

                        }
                        else if (success.equals("0")) {
                            Toast.makeText(getApplicationContext(), "user is not Registered, Please Register", Toast.LENGTH_LONG).show();

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Login Error !1" + e, Toast.LENGTH_LONG).show();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(BuyerLogin.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("phn_num", phn_num);
                    params.put("password", password);
                    return  params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    }*/
