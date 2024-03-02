package com.example.e_udhyog;


import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Subcategory_art extends AppCompatActivity {

    ListView listView;

    List<Subcategory> subcategoryArrayList ;

    String url = " http://192.168.72.108/eudhyog/subcategory_art.php";
    Subcategory subcategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategory_list);

        listView = findViewById(R.id.myListView);
        subcategoryArrayList   =new ArrayList<>();


        retrieveData();


    }

    public void retrieveData() {

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (sucess.equals("1")) {


                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);


                                    Subcategory subcategory = new Subcategory(object.getString("subcat_name"));

                                    subcategoryArrayList.add(subcategory);


                                }

                                Subcategory_listAdapater adapter = new Subcategory_listAdapater(subcategoryArrayList, getApplicationContext());
                                listView.setAdapter(adapter);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Subcategory_art.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
}
