package com.example.e_udhyog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Subcategory_listAdapater  extends ArrayAdapter<Subcategory> {

    Context context;
    List<Subcategory> arrayListsubcategory;
    TextView tvName;


    public Subcategory_listAdapater(List<Subcategory> arrayListsubcategory, Context context) {
        super(context, R.layout.custom_list_subcategory, arrayListsubcategory);

        this.context = context;
        this.arrayListsubcategory = arrayListsubcategory;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View listviewitem = inflater.inflate(R.layout.custom_list_subcategory, null, true);


        tvName = listviewitem.findViewById(R.id.txt_name);

        Subcategory listData = arrayListsubcategory.get(position);


        tvName.setText(listData.getSubcat_name());

        return listviewitem;
    }
}