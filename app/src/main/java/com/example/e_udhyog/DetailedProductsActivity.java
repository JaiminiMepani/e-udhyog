package com.example.e_udhyog;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class DetailedProductsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private ImageView mImage;
    private TextView mTitle, mDetails, mPrice,mOther,Mqty,Msold,Maddress,Mdelivery;
      EditText qty;
     String order_qty,min_quantity;    Button buy;
      int min,addqty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);


        mImage = findViewById(R.id.image_view);
        mPrice = findViewById(R.id.price);
        mDetails= findViewById(R.id.details);
        mTitle = findViewById(R.id.name);
        mOther=findViewById(R.id.otherdetail);
        Mqty=findViewById(R.id.getqty);
        Msold=findViewById(R.id.sold);
        Maddress=findViewById(R.id.storeaddress);
        Mdelivery=findViewById(R.id.exp_delivery);
        buy=findViewById(R.id.buynow);
      qty=findViewById(R.id.editqty);



        // Catching incoming intent
        Intent intent = getIntent();
      String p_price = intent.getStringExtra("p_price");
       String p_details = intent.getStringExtra("p_details");
        String p_name= intent.getStringExtra("p_name");
        String p_image = intent.getStringExtra("p_image");
        String other_details = intent.getStringExtra("other_details");
        String storename=intent.getStringExtra("storename");
      String address=intent.getStringExtra("address");
      String delivery_days=intent.getStringExtra("delivery_days");
       min_quantity = intent.getStringExtra("min_quantity");






        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order_qty = qty.getText().toString();
                min = parseInt(min_quantity);
                addqty = parseInt(order_qty);
                   if (addqty < min) {
                       Toast.makeText(DetailedProductsActivity.this, "Enter minimum quantity", Toast.LENGTH_SHORT).show();
                   }

            else {
                    Intent intent1 = new Intent(DetailedProductsActivity.this, DeliveryMain.class);

                    intent1.putExtra("p_price", p_price);
                    intent1.putExtra("min_quantity", min);
                    intent1.putExtra("order_qty", addqty);
                    startActivity(intent1);


                }
            }
        });


     if (intent !=null){


            mTitle.setText(p_name);
            mPrice.setText(p_price);
            mDetails.setText(p_details);
            mOther.setText(other_details);
          Mqty.setText(min_quantity);
          Msold.setText(storename);
          Maddress.setText(address);
          Mdelivery.setText(delivery_days);
            Glide.with(DetailedProductsActivity.this).load(p_image).into(mImage);
        }

    }
}
