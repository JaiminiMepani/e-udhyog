package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Addproduct_Finish extends AppCompatActivity {
    Button add,finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct_finish);

        add = findViewById(R.id.add_product);
        finish = findViewById(R.id.finish);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Addproduct_Finish.this, SellerCategory.class);
                startActivity(intent);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Addproduct_Finish.this,SellerMain.class);
                startActivity(intent);}
        });
    }


}

