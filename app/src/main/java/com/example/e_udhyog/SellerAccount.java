package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SellerAccount extends AppCompatActivity {
    Button  s_Myprofile, s_bank,s_logout,s_deliverypartner,inventory,Reports;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_acc);
        Reports=findViewById(R.id.reports);

      s_Myprofile = findViewById(R.id.s_profilebtn);
    s_Myprofile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerAccount.this, SellerMyProfile.class);
                startActivity(intent);
            }
        });

        s_bank = findViewById(R.id.s_bankdetails);
        s_bank.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerAccount.this, BankDetails.class);
                startActivity(intent);
            }
        });

        s_logout = findViewById(R.id.slogout);
        s_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SellerAccount.this, AlreadySelling.class);
                startActivity(intent);
            }
        });

        s_deliverypartner= findViewById(R.id.deliverypartner);
        s_deliverypartner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent intent = new Intent(SellerAccount.this, DeliveryPartner.class);
                startActivity(intent);
            }
        });

       inventory = findViewById(R.id.s_catalog);
       inventory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent intent = new Intent(SellerAccount.this,Seller_inventory.class);
                startActivity(intent);
            }
        });
        Reports.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent intent = new Intent(SellerAccount.this,Seller_Reports.class);
                startActivity(intent);
            }
        });
    }
}

