package com.example.e_udhyog;

import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {
    public static final String STRING = "";
    RadioGroup radiogroup;
    RadioButton Cash, Online,radioButton;
    String select;
    Button place;
    int total,q_price,qty;

    String p_price, min_quantity,selected,bname, bphone , b_address, b_city, b_state, b_pincode,
            order_status="1",order_qty;
    TextView price, Bill,Qty;


    String URL = "http://192.168.163.108/eudhyog/order.php";
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        radiogroup=findViewById(R.id.payment_radio);
       Cash=findViewById(R.id.cash);
       Online=findViewById(R.id.online);
       place=findViewById(R.id.order);
       price=findViewById(R.id.itemsprice);
       Bill=findViewById(R.id.bill);
       Qty=findViewById(R.id.p_qty);

        p_price = getIntent().getStringExtra("p_price");
        min_quantity= getIntent().getStringExtra("min_quantity");
     selected = getIntent().getStringExtra("selected");
     bname=getIntent().getStringExtra("bname");
     bphone=getIntent().getStringExtra("bphone");
     b_address=getIntent().getStringExtra("b_address");
     b_state=getIntent().getStringExtra("b_state");
    b_city=getIntent().getStringExtra("b_city");
     b_pincode=getIntent().getStringExtra("b_pincode");
     order_qty=getIntent().getStringExtra("order_qty");


   Qty.setText(order_qty);
    q_price= parseInt(p_price) ;
    qty=parseInt(order_qty);
     price.setText("₹ "+ p_price);

        total =  q_price * qty +49;
      Bill.setText(String.valueOf("₹ " +total));

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               confirm();
                System.out.println("fbfbfdbfbf" +total);
//order_qty,p_price,total,payment_option,delivery_option,b_address,b_city,b_state,b_pincode,order_status
            }
        });
    }

    private void order() {
        //String order_qty,String p_price, String total, String payment_option, String delivery_option,String b_address,
        //                       String b_city,String b_state,String b_pincode,String order_status

        final String payment_option=((RadioButton) findViewById(radiogroup.getCheckedRadioButtonId())).getText().toString();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("anyText", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                                Intent intent=new Intent(Payment.this,BuyerMain.class);
                                startActivity(intent);


                            } else if (success.equals("0")) {

                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Registration Error !2" + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("order_qty", String.valueOf(order_qty));
               params.put("p_price", p_price);
                params.put("total", String.valueOf(total));
                params.put("payment_option", payment_option);
              params.put("delivery_option",selected);
                params.put("address",b_address);
                params.put("city",b_city);
                params.put("state",b_state);
                params.put("pincode",b_pincode);
                params.put("order_status", order_status);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public void confirm() {
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(Payment.this);

        // Set the message show for the Alert time
        builder.setMessage("Confirm Your Order?");

        // Set Alert Title
        builder.setTitle("Confirmation!!!");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            order();
            finish();


        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

}