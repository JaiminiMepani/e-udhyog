package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SellerCatalog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_catalog);
        Spinner spinner = findViewById(R.id.gst_spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("0%");
        arrayList.add("5%");
        arrayList.add("12%");
        arrayList.add("18%");
        arrayList.add("28%");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


        Spinner d_spinner = findViewById(R.id.days_spinner);
        ArrayList<String> arraydays = new ArrayList<>();
        arraydays.add("3-5 days");
        arraydays.add("7-10days");
        arraydays.add("11-15 days");
        ArrayAdapter<String> arrayAdapt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arraydays);
        arrayAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        d_spinner.setAdapter(arrayAdapt);



        Button Finish=findViewById(R.id.finish_btn);
        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SellerCatalog.this,SellerMain.class);
                startActivity(intent);}
        });
    }
}


