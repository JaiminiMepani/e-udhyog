package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class BuyerMain extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter mAdapter;

    private List<Product> products;
    private static final String url= "http://192.168.163.108/eudhyog/getproducts.php";
    ImageView Food,Clothing,Home,Art,Accessories;
    ImageButton b_MyAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_main);



        b_MyAcc = findViewById(R.id.b_myacc);
            Food=findViewById(R.id.food);
        Clothing=findViewById(R.id.clothing);
      Home=findViewById(R.id.homedecor);
        Art=findViewById(R.id.art);
        Accessories=findViewById(R.id.access);

        b_MyAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerMain.this, BuyerMyAccount.class);
                startActivity(intent);
            }
        });

      Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerMain.this, Subcategory_List.class);

                startActivity(intent);
            }
        });

     Clothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BuyerMain.this, Subcategory_clothing.class);
                startActivity(intent1);
            }
        });

       Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BuyerMain.this, Subcategory_homedecor.class);
                startActivity(intent1);
            }
        });
      Art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BuyerMain.this, Subcategory_art.class);
                startActivity(intent1);
            }
        });

        Accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BuyerMain.this, Subcategory_accessories.class);
                startActivity(intent1);
            }
        });


        //initializing the productlist


        recyclerView = findViewById(R.id.recyclerview);
        //recyclerView.setHasFixedSize(true);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));

        manager = new LinearLayoutManager(BuyerMain.this);
        recyclerView.setLayoutManager(manager);
        products = new ArrayList<>();

        getProducts();


       /* ImageButton b_Mycart = findViewById(R.id.b_mycart);
        b_Mycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerMain.this, CartMain.class);
                startActivity(intent);
            }
        });*/



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
                           String  delivery_days= object.getString("delivery_days");
                                String address= object.getString("address");
                                String state= object.getString("state");
                                String storename=object.getString("storename");



                                // String rate = String.valueOf(rating);
                                //  float newRate = Float.valueOf(rate);

                                Product product = new Product(p_id,p_image,p_name,p_price, p_details,
                                        other_details,min_quantity,delivery_days,address,state,storename);
                                products.add(product);


                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        mAdapter = new ProductAdapter(BuyerMain.this,products);
                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(BuyerMain.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(BuyerMain.this).add(stringRequest);

    }

}