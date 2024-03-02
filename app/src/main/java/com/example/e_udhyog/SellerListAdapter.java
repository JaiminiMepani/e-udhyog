package com.example.e_udhyog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

    public class SellerListAdapter extends RecyclerView.Adapter<com.example.e_udhyog.ProductAdapter.MyViewHolder> {

        private Context context;
        private List<Product> products = new ArrayList<>();


        public SellerListAdapter( Context context,List<Product>  products){
            this.context= context;
            this.products = products;

        }
        public  static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView mTitle, mPrice, mdetails,mother,mmin,mdelivery;
            ImageView mImageView;
            ArrayList<Product> ProductArrayList = new ArrayList<>();
            Context context;


            public MyViewHolder(View view) {
                super(view);


                mTitle = view.findViewById(R.id.product_title1);
                mImageView = view.findViewById(R.id.product_image1);
                mdetails = view.findViewById(R.id.product_details1);
                mPrice = view.findViewById(R.id.product_price1);
                mother = view.findViewById(R.id.other_details1);
                mmin = view.findViewById(R.id.min1);
                //  mdelivery = view.findViewById(R.id.delivery_day);
            }
        }


        @Override
        public com.example.e_udhyog.ProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.seller_productlist, parent, false);

            return new com.example.e_udhyog.ProductAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(com.example.e_udhyog.ProductAdapter.MyViewHolder holder, int position) {

            Product product = products.get(position);


            holder.mTitle.setText(product.getP_Name());
            holder.mPrice.setText("INR  " + product.getP_Price());
            holder.mdetails.setText(product.getP_Details());
            holder.mother.setText(product.getOtherdetails());
            holder.mmin.setText("Min Qty : " +product.getMin_quantity());
            //  holder.mdelivery.setText(product.getDelivery_days());

            Glide.with(context)
                    .load(product.getP_Image())
                    .into(holder.mImageView);

        }

        @Override
        public int getItemCount() {
            return products.size();
        }
    }


