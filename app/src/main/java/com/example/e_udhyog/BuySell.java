package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;


public class BuySell extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_sell);


                ImageButton Buyer = (ImageButton)findViewById(R.id.buyer);
                ImageButton Seller = (ImageButton)findViewById(R.id.seller);


                Buyer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                                Intent intent=new Intent(BuySell.this,LoginRegister.class);
                                startActivity(intent);}
                });
        Seller.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(BuySell.this,SellerFirst.class);
                        startActivity(intent);}
                });
            }


        }



