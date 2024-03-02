package com.example.e_udhyog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Add_popup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct_popup);

        AlertDialog.Builder  builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("ADD QUANTITY OF PRODUCT");
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View viewInflated= layoutInflater.inflate(R.layout.addproduct_popup,null);
        builder.setView(viewInflated);

         final EditText input= viewInflated.findViewById(R.id.addqty);


        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                 String m_text=input.getText().toString();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }



}
