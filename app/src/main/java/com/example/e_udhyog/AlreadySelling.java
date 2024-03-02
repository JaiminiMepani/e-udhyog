package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class AlreadySelling extends AppCompatActivity {
    String URL_LOGIN = "http://192.168.72.108/eudhyog/s_login.php";
    String phn_num, password;
    EditText etPhone, etPassword;
    Button S_Login;
    TextView S_Forgotpassword;

    public static final String KEY_ID = "s_id";

    private final String DATA_STATUS = "status";
    public static final String KEY_PHONE = "phn_num";

    protected JSONObject loginData;
    private Session session;
    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";
    public static final String KEY_NAME = "storename";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new Session(AlreadySelling.this);

        if (session.isLoggedIn()) {
            Intent mainScreen = new Intent(getApplicationContext(), SellerMain.class);
            mainScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainScreen);
            finish();
        }
        setContentView(R.layout.already_selling);


        etPhone = findViewById(R.id.s_phn);
        etPassword = findViewById(R.id.s_pass);
        S_Forgotpassword = findViewById(R.id.s_forgotpassword);
        S_Login = findViewById(R.id.s_loginbtn);


        S_Forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlreadySelling.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        S_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phn_num = etPhone.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                if (phn_num.isEmpty() || password.isEmpty()) {
                    Toast.makeText(AlreadySelling.this, "please enter valid data", Toast.LENGTH_SHORT).show();
                } else {


                    Intent i = new Intent(AlreadySelling.this, SellerMain.class);
                    startActivity(i);
                }
            }
        });

    }

  /*  private void Login() {

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
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
                                          jsonObject.getString("s_id");
                                Intent i = new Intent(AlreadySelling.this, SellerMain.class);
                                startActivity(i);

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

                params.put("phn_num", phn_num);
                params.put("password", password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    public void handleLoginResponse() {
        if (loginData == null) {
            Toast.makeText(getApplicationContext(),"message",Toast.LENGTH_LONG).show();
        } else {
            try {
                if (loginData.getString(DATA_STATUS).equals("granted")) {
                    session.createLoginSession(
                            loginData.getString(KEY_ID),
                            loginData.getString(KEY_PHONE),
                            loginData.getString(KEY_NAME));

                    Intent featuredScreen = new Intent(getBaseContext(), SellerMain.class);
                    featuredScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(featuredScreen);
                    finish();
                } else {

                }
            } catch (JSONException e) {

            }
        }
    }*/
}