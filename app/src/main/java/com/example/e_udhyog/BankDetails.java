package com.example.e_udhyog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class BankDetails extends AppCompatActivity {

    Button sbutton;
    String bank_name, branch_name, acc_holdername, acc_no,c_acc, ifsc, upi;


    String url="http://192.168.13.108/eudhyog/bankdetails.php";

    EditText etbank_name,etbranch,  etaccname, etaccno,etcaccno, etifsc,etupi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_bankdetails);

        etbank_name=(EditText) findViewById(R.id.bankname);
        etbranch = (EditText) findViewById(R.id.branchname);
        etaccname= (EditText) findViewById(R.id.accname);
        etaccno= (EditText) findViewById(R.id.acc_no);
        etifsc=(EditText)  findViewById(R.id.ifsc_no);
        etupi = (EditText) findViewById(R.id.upiid);
        etcaccno= (EditText) findViewById(R.id.c_accno);

        sbutton=(Button) findViewById(R.id.save_button);

        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bank_name= etbank_name.getText().toString().trim();
                branch_name=  etbranch.getText().toString().trim();
                acc_holdername=  etaccname.getText().toString().trim();
                acc_no= etaccno.getText().toString().trim();
                ifsc=  etifsc.getText().toString().trim();
                upi=   etupi.getText().toString().trim();
                 c_acc =etcaccno.getText().toString();


                if (!c_acc.equals(acc_no)) {
                    Toast.makeText(BankDetails.this, "Account Number do not match", Toast.LENGTH_SHORT).show();
                }
                 else {
                    bank_details(bank_name, branch_name, acc_holdername, acc_no, ifsc, upi);
                }
            }
        });
    }

    private void bank_details(String bank_name, String branch_name, String acc_holdername, String acc_no, String ifsc, String upi )
    {


        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("anyText",response);
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");


                            if(success.equals("1")){
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();


                            }
                            else if(success.equals("3"))
                            {
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

                            }

                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String,String>();

                params.put("bank_name",bank_name);
                params.put("branch_name",branch_name);
                params.put("acc_holdername",acc_holdername);
                params.put("acc_no",acc_no);
                params.put("ifsc",ifsc);
                params.put("upi",upi);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}




