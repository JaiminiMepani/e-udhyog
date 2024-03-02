package com.example.e_udhyog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private Context context;
    private List<Product> products = new ArrayList<>();


    public ProductAdapter( Context context,List<Product>  products){
        this.context= context;
        this.products = products;

    }
    public  static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle, mPrice, mdetails,mother,mmin,mdelivery,msold,maddress;

        ImageView mImageView;
        private LinearLayout mContainer;




        public MyViewHolder(View view) {
            super(view);


            mTitle = view.findViewById(R.id.product_title);
            mImageView = view.findViewById(R.id.product_image);
            mdetails = view.findViewById(R.id.product_details);
            mPrice = view.findViewById(R.id.product_price);
            mother = view.findViewById(R.id.other_details);
            mmin = view.findViewById(R.id.min);
           mdelivery = view.findViewById(R.id.deliveryday);
          msold=view.findViewById(R.id.soldby);
          maddress=view.findViewById(R.id.soldbyaddress);
            mContainer = view.findViewById(R.id.product_container);


        }


    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_list, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Product product = products.get(position);


        holder.mTitle.setText(product.getP_Name());
        holder.mPrice.setText("INR  " + product.getP_Price());
        holder.mdetails.setText(product.getP_Details());
        holder.mother.setText(product.getOtherdetails());
        holder.mmin.setText("Min Qty: " +product.getMin_quantity());
        holder.mdelivery.setText(product.getDelivery_days());
        holder.msold.setText("SOLD BY : " +product.getStorename());
        holder.maddress.setText("Seller Address : " +product.getAddress() +" , " +product.getState());

        Glide.with(context)
                .load(product.getP_Image())
                .into(holder.mImageView);

        System.out.println("vbfgbfg" + product.getDelivery_days());
        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,DetailedProductsActivity.class);

                intent.putExtra("p_name","Product Name : " +product.getP_Name());
                intent.putExtra("p_price",product.getP_Price());
                intent.putExtra("p_details","Product details: " +product.getP_Details());
                intent.putExtra("other_details","Product other details : " +product.getOtherdetails());
                intent.putExtra("min_quantity",product.getMin_quantity());
                intent.putExtra("storename","SOLD BY : " +product.getStorename());
               intent.putExtra("address", "Store Address : " +product.getAddress() + " , " +product.getState());
                intent.putExtra("p_image",product.getP_Image());


              context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}


