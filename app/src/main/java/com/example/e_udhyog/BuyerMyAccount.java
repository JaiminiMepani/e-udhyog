package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BuyerMyAccount extends AppCompatActivity {


    Button b_profile, selleracc,b_logout,help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyer_myaccount);

        b_profile = findViewById(R.id.buyerprofile);
        selleracc = findViewById(R.id.selleracc);
        b_logout = findViewById(R.id.buyer_logout);
       help = findViewById(R.id.buyerhelp);

        b_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerMyAccount.this, BuyerMyprofile.class);
                startActivity(intent);
            }
        });


        selleracc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerMyAccount.this,SellerFirst.class);
                startActivity(intent);
            }
        });

      help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerMyAccount.this,Help.class);
                startActivity(intent);
            }
        });
        b_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerMyAccount.this,BuyerLogin.class);
                startActivity(intent);
            }
        });


    }
}