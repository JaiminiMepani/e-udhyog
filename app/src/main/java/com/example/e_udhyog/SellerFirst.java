package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SellerFirst extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_first);

        Button Newseller =findViewById(R.id.choose_newseller);
        Button  Oldseller =findViewById(R.id.choose_oldseller);
       Newseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SellerFirst.this,StartSelling.class);
                startActivity(intent);}
        });
        Oldseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SellerFirst.this,AlreadySelling.class);
                startActivity(intent);}
        });
    }


}