package com.example.e_udhyog;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.HashMap;

public class CartMain extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public RequestQueue mQueue;
    public int order_id;
    HashMap<Integer,Integer> cartlist ;
    ArrayList<Product> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


     //   sharedPreferences = getSharedPreferences(Login.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Bundle extra = getIntent().getBundleExtra("cart");
        list = (ArrayList<Product>) extra.getSerializable("objects");
      /*  if (list.isEmpty()) {
            setContentView(R.layout.empty_cart);
            Button shop = findViewById(R.id.shopnow);
            shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        } else {*/
            setContentView(R.layout.cart);
            TextView totalPrice = findViewById(R.id.totalprice);
            ListView listcart = findViewById(R.id.cartlist);
            int Price = 0;
           // mQueue = VolleySingleton.getInstance(this).getmRequestqueue();
            CartAdapter adapter = new CartAdapter(this, R.layout.cart_list, list);
            listcart.setAdapter(adapter);
            cartlist = new HashMap<>();
          /*  for (Product m : list) {
                Price += Integer.parseInt(m.getP_Price().substring(0, m.getP_Price().indexOf("R")).trim()) * m.getQuant();
                cartlist.put(m.getId(), m.getMin_quantity());
            }*/
            //Toast.makeText(this,Integer.toString(adapter.totalprice),Toast.LENGTH_LONG).show();
            totalPrice.setText(Integer.toString(Price) + " Rs");
            String address = sharedPreferences.getString("add", "");
            String Name = sharedPreferences.getString("un", "");
            TextView cartName = findViewById(R.id.cartName);
            TextView add = findViewById(R.id.addresscart);
            cartName.setText(Name);
            add.setText(address);
            Button order = findViewById(R.id.order);
            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });

        }

    }


