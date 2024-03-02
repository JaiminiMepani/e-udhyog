package com.example.e_udhyog;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class CartAdapter extends ArrayAdapter<Product> {
    Context c;
    int resource;

    public CartAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> objects) {
        super(context, resource, objects);
        this.c=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String Name = getItem(position).getP_Name();
        String Qty = (getItem(position).getMin_quantity());
        String  cost = getItem(position).getP_Price();
      //  int costint = Integer.parseInt(cost.substring(0,cost.indexOf("R")).trim());
     //   String Total = (costint*getItem(position).getMin_quantity()).concat(" Rs");


        LayoutInflater inflater = LayoutInflater.from(c);
        convertView = inflater.inflate(resource,parent,false);

        TextView PName = (TextView) convertView.findViewById(R.id.Pname);
        TextView Pqty = (TextView) convertView.findViewById(R.id.Pqty);
        TextView Pprice = (TextView) convertView.findViewById(R.id.Pprice);
        TextView Ptotal = (TextView) convertView.findViewById(R.id.Ptotal);



        PName.setText(Name);
        Pqty.setText(Qty);
       // Pprice.setText(cost);
        //Ptotal.setText(Total);


        return convertView;
    }
}
/*import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.CartViewHolder > {
    Context context;
    ArrayList<Product> cart;



    public CartAdapter(Context context, ArrayList<Product> cart) {
        this.context = context;
        this.cart = cart;
       // this.cartListener = cartListener;

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cart.get(position);
        holder.mTitle.setText(product.getP_Name());
        holder.mPrice.setText("INR  " + product.getP_Price());
        holder.mdetails.setText(product.getP_Details());
        holder.mother.setText(product.getOtherdetails());
         holder.mmin.setText(product.getMin_quantity());
        //  holder.mdelivery.setText(product.getDelivery());

        Glide.with(context)
                .load(product.getP_Image())
                .into(holder.mImageView);
        holder.binding.quantity.setText(product.getQuantity() + " item(s)");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuantityDialogBinding quantityDialogBinding = QuantityDialogBinding.inflate(LayoutInflater.from(context));

                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setView(quantityDialogBinding.getRoot())
                        .create();

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));

                quantityDialogBinding.productName.setText(product.getName());
                quantityDialogBinding.productStock.setText("Stock: " + product.getStock());
                quantityDialogBinding.quantity.setText(String.valueOf(product.getQuantity()));
                int stock = product.getStock();


                quantityDialogBinding.plusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int quantity = product.getQuantity();
                        quantity++;

                        if (quantity > product.getStock()) {
                            Toast.makeText(context, "Max stock available: " + product.getStock(), Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            product.setQuantity(quantity);
                            quantityDialogBinding.quantity.setText(String.valueOf(quantity));
                        }

                        notifyDataSetChanged();
                        cart.updateItem(product, product.getQuantity());
                        cartListener.onQuantityChanged();
                    }
                });

                quantityDialogBinding.minusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int quantity = product.getQuantity();
                        if (quantity > 1)
                            quantity--;
                        product.setQuantity(quantity);
                        quantityDialogBinding.quantity.setText(String.valueOf(quantity));

                        notifyDataSetChanged();
                        cart.updateItem(product, product.getQuantity());
                        cartListener.onQuantityChanged();
                    }
                });

                quantityDialogBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
//                        notifyDataSetChanged();
//                        cart.updateItem(product, product.getQuantity());
//                        cartListener.onQuantityChanged();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle, mPrice, mdetails,mother,mmin,mdelivery;
        ImageView mImageView;
        ArrayList<Product> ProductArrayList = new ArrayList<>();
        Context context;


        public CartViewHolder(View view) {
            super(view);


            mTitle = view.findViewById(R.id.product_title);
            mImageView = view.findViewById(R.id.product_image);
            mdetails = view.findViewById(R.id.product_details);
            mPrice = view.findViewById(R.id.product_price);
            mother = view.findViewById(R.id.other_details);
            mmin = view.findViewById(R.id.min);
            //   mdelivery = view.findViewById(R.id.delivery_days);
        }
    }

}*/
