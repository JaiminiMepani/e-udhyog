package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SellerMain extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    TextView STORENAME;
    private RecyclerView.Adapter mAdapter;
    private List<Product> products;
    private static final String url= "http://192.168.163.108/eudhyog/getsellerproducts.php?s_id=35";
    private static final String url1= "http://192.168.163.108/eudhyog/storename.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_main);
         STORENAME=findViewById(R.id.store_name);
        recyclerView = findViewById(R.id.recyclerview);
        //recyclerView.setHasFixedSize(true);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));

        manager = new LinearLayoutManager(SellerMain.this);
        recyclerView.setLayoutManager(manager);
        products = new ArrayList<>();

         getstorename();
        getProducts();


        ImageButton s_myacc = findViewById(R.id.s_accbtn);
       s_myacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerMain.this, SellerAccount.class);
                startActivity(intent);
            }
        });
    }


    private void getProducts (){


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i<array.length(); i++) {

                                JSONObject object = array.getJSONObject(i);
                                /*products.add(new Product(
                                        product.getInt("p_id"),
                                        product.getString("p_name"),
                                        product.getDouble("p_price"),
                                        product.getString("p_details"),
                                        product.getString("p_image")
                                ));*/
                                String p_id=object.getString("p_id");
                                String p_image = object.getString("p_image");
                                String p_name = object.getString("p_name");
                                String  p_price = object.getString ("p_price");
                                String p_details = object.getString("p_details");
                                String other_details = object.getString("other_details");
                                String min_quantity=object.getString("min_quantity");
                                // String delivery_days= object.getString("delivery_days");



                                // String rate = String.valueOf(rating);
                                //  float newRate = Float.valueOf(rate);

                                Product product = new Product(p_id,p_image,p_name,p_price, p_details,
                                        other_details,min_quantity);
                                products.add(product);
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        mAdapter = new SellerListAdapter(SellerMain.this,products);
                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(SellerMain.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(SellerMain.this).add(stringRequest);

    }
    private void getstorename (){


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                             STORENAME.setText(response.replaceAll("\"",""));

                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(SellerMain.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(SellerMain.this).add(stringRequest);

    }

}

