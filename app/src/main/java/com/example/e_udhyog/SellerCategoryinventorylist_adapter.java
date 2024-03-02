package com.example.e_udhyog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SellerCategoryinventorylist_adapter extends RecyclerView.Adapter<SellerCategoryinventorylist_adapter .MyViewHolder> {

        private Context context;
        private List<Product> products = new ArrayList<>();
        private static final String url1= "http://192.168.163.108/eudhyog/removeproduct.php";
    private static final String url="http://192.168.163.108/eudhyog/sellerproduct_retrival.php?apicall=updatehero";
    /* public interface OnItemClickListener
{
    void onItemClick (int position);


}
public void setOnItemClickListener(OnItemClickListener clickListener)
{
    listener=clickListener;
}*/

        public SellerCategoryinventorylist_adapter (Context context, List<Product> products) {
            this.context = context;
            this.products = products;

        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView r1,r2,r3;
            EditText r4;
            Button r5,r6;
            String add;



            public MyViewHolder(@NonNull View view) {
                super(view);


              r1 = view.findViewById(R.id.row1);
               r2 = view.findViewById(R.id.row2);
            r3 = view.findViewById(R.id.row3);
            r4 = view.findViewById(R.id.row4);
             r5 = view.findViewById(R.id.row5);
           r6 = view.findViewById(R.id.row6);




            }


        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.catlag_inventorylist, parent, false);

            return new MyViewHolder(view);

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

            Product product = products.get(position);


            holder.r1.setText("Product Name : " + product.getP_Name());
            holder.r2.setText( "Product Qty : " +product.getP_quantity());
            holder.r3.setText("Remaining Qty : 5" );
         //   holder.r4.getText().toString();




           holder.r5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //listener.onItemClick(getAdapterPosition());

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.e("anyText", response);
                                    try {

                                        JSONObject object = new JSONObject(response);
                                        String check = object.getString("state");


                                        if (check.equals("delete")) {
                                            removeAt(position);
                                            Toast.makeText(context, "Product deleted successfully ", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(context, "Nothing to delete, provide an name please", Toast.LENGTH_SHORT).show();
                                        }

                                    } catch (JSONException jsonException) {
                                        jsonException.printStackTrace();
                                    }

                                }



                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                            Toast.makeText(context, error.toString(),Toast.LENGTH_LONG).show();

                        }
                    })
                    {

                        @Override
                        protected Map<String, String> getParams() {
                            Map<String,String> params = new HashMap<>();

                            params.put("p_name", product.getP_Name());


                            return params;
                        }
                    };
                    Volley.newRequestQueue(context).add(stringRequest);

                }

            });
            holder.r6.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.e("anyText", response);
                                    try {

                                        JSONObject object = new JSONObject(response);
                                        String error = object.getString("error");


                                        if (error.equals("false")) {

                                            Toast.makeText(context, "Product updated successfully ", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(context, "Nothing to update,add quantity please", Toast.LENGTH_SHORT).show();
                                        }

                                    } catch (JSONException jsonException) {
                                        jsonException.printStackTrace();
                                    }

                                }



                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                            Toast.makeText(context, error.toString(),Toast.LENGTH_LONG).show();

                        }
                    })
                    {

                        @Override
                        protected Map<String, String> getParams() {
                            Map<String,String> params = new HashMap<>();

                            params.put("p_name", product.getP_Name());
                            params.put("p_quantity",holder.r4.getText().toString());


                            return params;
                        }
                    };
                    Volley.newRequestQueue(context).add(stringRequest);

                }


            });


        }



        @Override
        public int getItemCount() {
            return products.size();


        }
public  void removeAt(int position)
        {
            products.remove(position);
            notifyItemRemoved(position);
        }


    }

