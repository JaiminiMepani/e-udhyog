package com.example.e_udhyog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeliveryPartner extends AppCompatActivity
{

    Spinner spinnerdelivery;
    ArrayList<String> deliveryList = new ArrayList<>();

    ArrayAdapter<String> deliveryAdapter;

    RequestQueue requestQueue;
    String deliverypartner_name;
    EditText etd_name;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_partner);
        requestQueue = Volley.newRequestQueue(this);
        spinnerdelivery = findViewById(R.id.spinner_delivery);
        etd_name = findViewById(R.id.name_Deliverypartner);
        save = findViewById(R.id.d_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deliverypartner_name = etd_name.getText().toString().trim();
                savedata();
            }
        });

        String url = "http://192.168.72.108/eudhyog/delivery_partner.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("delivery_partner");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String deliveryname = jsonObject.optString("deliverypartner_name");
                        deliveryList .add(deliveryname);
                        deliveryAdapter = new ArrayAdapter<>(DeliveryPartner.this,
                                android.R.layout.simple_spinner_item, deliveryList );
                        deliveryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerdelivery.setAdapter(deliveryAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);




    }

    private void savedata()
    {
        String URL_LOGIN = "http://192.168.72.108/eudhyog/add_deliverypartner.php";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
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



                            }
                            else{
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

                            }

                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext()," Error "+error,Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();

                params.put("deliverypartner_name",deliverypartner_name);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}