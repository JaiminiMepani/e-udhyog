package com.example.e_udhyog;

import android.os.Bundle;
import android.widget.Button;
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

public class Seller_inventory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter mAdapter;

    private List<Product> products;
    Button remove,r6;

    private static final String url= "http://192.168.163.108/eudhyog/sellerproduct_retrival.php?apicall=getheroes";

   // private static final String url1= "http://192.168.163.108/eudhyog/sellerproduct_retrival.php?apicall=deletehero";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalog_inventory);

        recyclerView = findViewById(R.id.recyclerview1);
        //recyclerView.setHasFixedSize(true);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(this));

        manager = new LinearLayoutManager(Seller_inventory.this);
        recyclerView.setLayoutManager(manager);
        products = new ArrayList<>();

        mAdapter = new SellerCategoryinventorylist_adapter(Seller_inventory.this,products);
        recyclerView.setAdapter(mAdapter);


        getProducts();






    }


    private void getProducts (){


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject object = new JSONObject(response);
                            JSONArray array  = object.getJSONArray("seller_catalogue");

                            for (int i = 0; i<array.length(); i++) {

                                JSONObject jobject = array.getJSONObject(i);

                                String p_name = jobject.getString("p_name");

                                String p_quantity=jobject.getString("p_quantity");

                                Product product = new Product(p_name,p_quantity );
                                products.add(product);

                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        mAdapter = new SellerCategoryinventorylist_adapter(Seller_inventory.this,products);
                        recyclerView.setAdapter(mAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(Seller_inventory.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(Seller_inventory.this).add(stringRequest);

    }


   /* private void RemoveProduct (){

        String p_Name=getIntent().getStringExtra("p_name");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("anyText",response);
                        try {

                            JSONObject object = new JSONObject(response);
                            String success = object.getString("success");




                            if(success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Product deleted successfully ", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(Seller_inventory.this, "Nothing to delete, provide an name please", Toast.LENGTH_SHORT).show();
                            }


                        }}catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(Seller_inventory.this, error.toString(),Toast.LENGTH_LONG).show();

            }
        })
        {

            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();

                params.put("p_name",p_Name);


                return params;
            }
        };
        Volley.newRequestQueue(Seller_inventory.this).add(stringRequest);

    }
        */

}

