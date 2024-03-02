package com.example.e_udhyog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;



public class SellerCatalog extends AppCompatActivity {


    ImageView imageView;
    Button gallery_button, Next;
    String p_name, p_price, p_details, other_details, p_quantity, min_quantity, delivery_days;




     EditText etp_name,etp_price,  etp_details, etother_details, etp_quantity,etmin_quantity,etdelivery;
    Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="http://192.168.72.108/eudhyog/product_details.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_catalog);

        imageView = (ImageView) findViewById(R.id.view);
        gallery_button = (Button) findViewById(R.id.gallery);
        Next = findViewById(R.id.c_next);


        etp_name =(EditText) findViewById(R.id.pname);
        etp_price = (EditText) findViewById(R.id.pprice);
        etp_details = (EditText) findViewById(R.id.pdetails);
        etother_details = (EditText) findViewById(R.id.otherdetails);
        etp_quantity=(EditText)  findViewById(R.id.pquantity);
        etmin_quantity = (EditText) findViewById(R.id.minquantity);
        etdelivery=(EditText) findViewById(R.id.DELIVERY_days);



        gallery_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showFileChooser();

            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              uploadImage();

              //  p_details(p_name,p_price,p_details,other_details,p_quantity,min_quantity,delivery_days);


            }
        });

    }

 /*  @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if (requestCode == 3 && resultCode == RESULT_OK && data != null && data.getData() != null) {
             Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
                encodeBitmapImage(bitmap);
            } catch (Exception ex) {
                        ex.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

   private void encodeBitmapImage(Bitmap bitmap) {
     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();

    }*/

    public String getStringImage(Bitmap bmp){
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(SellerCatalog.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(SellerCatalog.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String p_image = getStringImage(bitmap);

                //Getting Image Name
                p_name= etp_name.getText().toString().trim();
                p_price= etp_price.getText().toString().trim();
                p_details= etp_details.getText().toString().trim();
                other_details= etother_details.getText().toString().trim();
                p_quantity= etp_quantity.getText().toString().trim();
                min_quantity=etmin_quantity.getText().toString().trim();
                delivery_days= etdelivery.getText().toString();


                //Creating parameters
                Map<String,String> params = new Hashtable<>();

                //Adding parameters
                params.put("p_image", p_image);
                params.put("p_name",p_name);
                params.put("p_price",p_price);
                params.put("p_details",p_details);
                params.put("other_details",other_details);
                params.put("p_quantity",p_quantity);
                params.put("min_quantity",min_quantity);
                params.put("delivery_days",delivery_days);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }}


   /* private void p_details(String p_name, String p_price, String p_details,String other_details,String p_quantity,String min_quantity,String delivery_days )
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

                                Intent i = new Intent(SellerCatalog.this,Addproduct_Finish.class);
                                startActivity(i);
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

                params.put("p_name",p_name);
                params.put("p_price",p_price);
                params.put("p_details",p_details);
                params.put("other_details",other_details);
                params.put("p_quantity",p_quantity);
                params.put("min_quantity",min_quantity);
                params.put("delivery_days",delivery_days);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}







  /* private void uploaddatatodb() {





        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                p_name.setText("");
                p_price.setText("");
                p_details.setText("");
                other_details.setText("");
              p_quantity.setText("");
                min_quantity.setText("");
                //imageView.setImageResource(R.drawable.ic_launcher_foreground);
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("p_image", encodeImageString);
                params.put(KEY_NAME,pname);
                params.put(KEY_PRICE,pprice);
                params.put(KEY_DETAIL,pdetails);
                params.put(KEY_OTHER,other);
                params.put(KEY_QTY,qty);
                params.put(KEY_MIN_QTY,min);
              //  params.put(KEY_DELIVERY,delivery_days);


                return params;
            }
        };


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}

        private void uploadBitmap( Bitmap bitmap){



            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, UPLOAD_URL,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {
                            try {
                                JSONObject obj=new JSONObject(new String(response.data));
                                //Showing toast message of the response
                                Toast.makeText(SellerCatalog.this, obj.getString("message"), Toast.LENGTH_LONG).show();

                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                            //Showing toast
                            Toast.makeText(SellerCatalog.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    })
            {

                protected Map<String, DataPart> getByteData() {
                    Map<String, DataPart> params = new HashMap<>();
                    long p_image = System.currentTimeMillis();
                    params.put(KEY_IMAGE, new DataPart(p_image + ".png", bitmap));
                    return params;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //Converting Bitmap to String


                    //Creating parameters
                    Map<String,String> params = new HashMap<>();

                    //Adding parameters

                    params.put(KEY_NAME,p_name);
                    params.put(KEY_PRICE,p_price);
                    params.put(KEY_DETAIL,p_details);
                    params.put(KEY_OTHER,other_details);
                    params.put(KEY_QTY,p_quantity);
                    params.put(KEY_MIN_QTY,min_quantity);
                    params.put(KEY_DELIVERY,delivery_days);

                    //returning parameters
                    return params;
                }

            };

            //adding the request to volley
            Volley.newRequestQueue(this).add(volleyMultipartRequest);




            }*/


