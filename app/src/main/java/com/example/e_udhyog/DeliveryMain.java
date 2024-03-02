package com.example.e_udhyog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class DeliveryMain extends AppCompatActivity {

    EditText bname, bphone, b_address, b_city, b_state, b_pincode;
    boolean isAllFieldsChecked = false;
    Button deliverybtn;
    RadioGroup rg;
    RadioButton self, home;
    String selected,p_price,min_quantity,order_qty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery);

        bname = (EditText) findViewById(R.id.b_nme);
        bphone = (EditText) findViewById(R.id.num);
        b_address = (EditText) findViewById(R.id.add);
        b_city = (EditText) findViewById(R.id.city);
        b_state = (EditText) findViewById(R.id.state);
        b_pincode = (EditText) findViewById(R.id.pincode);
        rg=findViewById(R.id.delivery_radio);
        self=findViewById(R.id.selfpickup);
        home=findViewById(R.id.homedelivery);



        p_price = getIntent().getStringExtra("p_price");
        min_quantity= getIntent().getStringExtra("min_quantity");
        order_qty=getIntent().getStringExtra("order_qty");


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.selfpickup)
                {
                    selected=self.getText().toString();
                }
                else
                {
                    selected=home.getText().toString();
                }
            }
        });

       deliverybtn = (Button) findViewById(R.id.delivery_btn);
        deliverybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // isAllFieldsChecked = CheckAllFields();
             //   if (isAllFieldsChecked) {
                    Intent intent = new Intent(DeliveryMain.this, Payment.class);
                intent.putExtra("order_qty",order_qty);

                intent.putExtra("p_price",p_price);

                intent.putExtra("min_quantity",min_quantity);
                    intent.putExtra("selected" ,selected);
                intent.putExtra("bname" ,bname.getText().toString());
                intent.putExtra("bphone",bphone.getText().toString());
                intent.putExtra("b_address",b_address.getText().toString());
                intent.putExtra("b_city" ,b_city.getText().toString());
                intent.putExtra("b_state" ,b_state.getText().toString());
                intent.putExtra("b_pincode" ,b_pincode.getText().toString());


                    startActivity(intent);

              //  }
            }
        });
    }

  /*  private boolean CheckAllFields() {

        String val = bname.getText().toString();
        String bphonenumber = bphone.getText().toString();
        String city = b_city.getText().toString();
        String address = b_address.getText().toString();
        String state = b_state.getText().toString();
        String pincode = b_pincode.getText().toString();


        if (TextUtils.isEmpty(val)) {
            Toast.makeText(DeliveryMain.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        }


        if (TextUtils.isEmpty(bphonenumber)) {
            Toast.makeText(DeliveryMain.this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT).show();
        }


        if (TextUtils.isEmpty(address)) {
            Toast.makeText(DeliveryMain.this, "Please Enter Your Address", Toast.LENGTH_SHORT).show();
        }


        if (TextUtils.isEmpty(city)) {
            Toast.makeText(DeliveryMain.this, "Please Enter Your City", Toast.LENGTH_SHORT).show();
        }


        if (TextUtils.isEmpty(state)) {
            Toast.makeText(DeliveryMain.this, "Please Enter Your State", Toast.LENGTH_SHORT).show();
        }


        return false;
    }*/
}
