package com.example.e_udhyog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class SellerCategory extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerCategory, spinnerSubcategory;
    ArrayList<String> categoryList = new ArrayList<>();
    ArrayList<Subcategory> subcategoryList = new ArrayList<>();
    ArrayAdapter<String> categoryAdapter;
    ArrayAdapter<String> subcategoryAdapter;
    RequestQueue requestQueue;
    public int subcat_id;
  TextView Subcat_name;
    LinearLayout subCateLyt;

    Button S_Next;
    ImageView imageView;
    Button gallery_button, Next;
    String  s_id, p_name, p_price, p_details, other_details, p_quantity, min_quantity, delivery_days;

    public static final String KEY_ID = "s_id";
    EditText etp_name, etp_price, etp_details, etother_details, etp_quantity, etmin_quantity, etdelivery;
    Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;
    private Session session;

    private String UPLOAD_URL = "http://192.168.163.108/eudhyog/product_details.php";
    String url = "http://192.168.163.108/eudhyog/categoryspinner.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_category);
        requestQueue = Volley.newRequestQueue(this);
        spinnerCategory = findViewById(R.id.category);
        spinnerSubcategory = findViewById(R.id.subcat);

        session=new Session(SellerCategory.this);

        imageView = (ImageView) findViewById(R.id.view);
        gallery_button = (Button) findViewById(R.id.gallery);
        Subcat_name=findViewById(R.id.subcatname);

        subCateLyt = findViewById(R.id.subcatlyt);

        etp_name = (EditText) findViewById(R.id.pname);
        etp_price = (EditText) findViewById(R.id.pprice);
        etp_details = (EditText) findViewById(R.id.pdetails);
        etother_details = (EditText) findViewById(R.id.otherdetails);
        etp_quantity = (EditText) findViewById(R.id.pquantity);
        etmin_quantity = (EditText) findViewById(R.id.minquantity);
        etdelivery = (EditText) findViewById(R.id.DELIVERY_days);

        S_Next = findViewById(R.id.s_next);
       // subCateLyt  .setOnClickListener(v ->  spinnerSubcategory.performClick());

        Subcat_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clickworking");
                spinnerSubcategory.performClick();
            }
        });

        gallery_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();

            }
        });

        S_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadImage();
                Intent intent = new Intent(SellerCategory.this, Addproduct_Finish.class);

                startActivity(intent);
            }
        });


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("category");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String categoryname = jsonObject.optString("c_name");
                        categoryList.add(categoryname);
                        categoryAdapter = new ArrayAdapter<>(SellerCategory.this,
                                android.R.layout.simple_spinner_item, categoryList);
                        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerCategory.setAdapter(categoryAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        spinnerCategory.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.category) {
            subcategoryList.clear();
            String selectedCategory = adapterView.getSelectedItem().toString();
            String url = "http://192.168.163.108/eudhyog/subcategory.php?c_name=" + selectedCategory;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        System.out.println("subcategory response"+ response);
                        JSONArray jsonArray = response.getJSONArray("sub_category");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Subcategory subcategory = new Subcategory();

                            subcategory.setSubcat_name(jsonObject.getString("subcat_name"));
                            subcategory.setSubcat_id(jsonObject.getString("subcat_id"));


                            subcategoryList.add(subcategory);
                        }
                        subCateLyt.setVisibility(View.VISIBLE);

                        CustomAdapter customAdapter = new CustomAdapter(subcategoryList, "subCate");

                        spinnerSubcategory.setAdapter(customAdapter);
                        spinnerSubcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                Subcategory subCate = subcategoryList.get(i);
                                System.out.println("cat name" + subCate.getSubcat_name());
                                //subcat_id = subCate.getSubcat_id();
                                System.out.println("id of subcategory" + subCate.getSubcat_id());

                               Subcat_name.setText(subCate.getSubcat_name());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(jsonObjectRequest);
            spinnerSubcategory.setOnItemSelectedListener(this);

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    public class CustomAdapter extends BaseAdapter {


        String type;
        private ArrayList<Subcategory> subcat;


        public CustomAdapter(ArrayList<Subcategory> subcat, String type) {
            this.type = type;
            this.subcat = subcat;

        }

        @Override
        public int getCount() {
            return subcat.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.lyt_spinner, null);
            Subcategory subcategory = subcat.get(i);

            TextView tvName=view.findViewById(R.id.tvName);

            //spinnerSubcategory = view.findViewById(R.id.subcat);
            tvName.setText(subcategory.getSubcat_name());

            return view;
        }



    }



        public String getStringImage(Bitmap bmp) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            return encodedImage;
        }


        private void uploadImage() {
            //Showing the progress dialog

           // Subcategory.subcat_id = Subcategory.getSubcat_id();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            try {
                                JSONObject responseJson = new JSONObject(s);


                                //Showing toast message of the response
                                Toast.makeText(SellerCategory.this, "data uploaded", Toast.LENGTH_LONG).show();


                            } catch (JSONException e) {

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            //Dismissing the progress dialog


                            //Showing toast
                            Toast.makeText(SellerCategory.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //Converting Bitmap to String
                    String p_image = getStringImage(bitmap);

                    //Getting Image Name
                    p_name = etp_name.getText().toString().trim();
                    p_price = etp_price.getText().toString().trim();
                    p_details = etp_details.getText().toString().trim();
                    other_details = etother_details.getText().toString().trim();
                    p_quantity = etp_quantity.getText().toString().trim();
                    min_quantity = etmin_quantity.getText().toString().trim();
                    delivery_days = etdelivery.getText().toString();


                    //Creating parameters
                    Map<String, String> params = new Hashtable<>();

                    //Adding parameters
                  params.put(KEY_ID,"35");
                 params.put("subcat_id",String.valueOf(subcat_id));
                    params.put("p_image", p_image);
                    params.put("p_name", p_name);
                    params.put("p_price", p_price);
                    params.put("p_details", p_details);
                    params.put("other_details", other_details);
                    params.put("p_quantity", p_quantity);
                    params.put("min_quantity", min_quantity);
                    params.put("delivery_days", delivery_days);

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
        }


    }






   /* ArrayList<String> category = new ArrayList<>();

    ArrayAdapter<String> subcategory_adapter;


    ArrayList<String> arraylist_clothing,arraylist_homedecor,arraylist_food,arraylist_artcraft
            ,arraylist_accessories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_category);


        Spinner spinner_category = (Spinner)findViewById (R.id.category);
        Spinner spinner_subcat = (Spinner) findViewById (R.id.subcat);


        Button S_Next =findViewById(R.id.s_next);
       S_Next.setOnClickListener(new View.OnClickListener() {


           @Override
            public void onClick(View view) {
                Intent intent=new Intent(SellerCategory.this,SellerCatalog.class);

                startActivity(intent);}
        });

        category .add("Clothing");
        category .add("HomeDecor");
        category .add("Food");
        category .add("Art&Craft");
        category .add("Accessories");

        ArrayAdapter<String> category_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,category);
        category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_category.setAdapter( category_adapter);



       arraylist_clothing=new ArrayList<>();
        arraylist_clothing.add("Shirts");
        arraylist_clothing.add("Tshirts");
        arraylist_clothing.add("Jeans");
        arraylist_clothing.add("Trousers");
        arraylist_clothing.add("Kurti");
        arraylist_clothing.add("Tops");
        arraylist_clothing.add("Sarees");


        arraylist_homedecor=new ArrayList<>();
        arraylist_homedecor.add("ShowPiece");
        arraylist_homedecor.add("Wall Hanging");
        arraylist_homedecor.add("Keychains");
        arraylist_homedecor.add("Lamps");
        arraylist_homedecor.add("Clocks");
        arraylist_homedecor.add("Curtains");
        arraylist_homedecor.add("Frames");


        arraylist_food=new ArrayList<>();
        arraylist_food.add("Namkeen");
        arraylist_food.add("Bakery Items");
        arraylist_food.add("Chocolates");
        arraylist_food.add("Masalas");
        arraylist_food.add("Oil");
        arraylist_food.add("Pickles");
        arraylist_food.add("Papad");
        arraylist_food.add("Dryfruits");


        arraylist_accessories=new ArrayList<>();
        arraylist_accessories.add("Jewellery");
        arraylist_accessories.add("Bags");
        arraylist_accessories.add("Caps");
        arraylist_accessories.add("Watch");
        arraylist_accessories.add("Footwear");


        arraylist_artcraft=new ArrayList<>();
        arraylist_artcraft.add("Painting");
        arraylist_artcraft.add("Paper Craft");
        arraylist_artcraft.add("Clay Models/Pottery");
        arraylist_artcraft.add("Embroidery");


         spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 if (i==0)
                 {
                  subcategory_adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arraylist_clothing);

                 }

                 if (i==1)
                 {
                     subcategory_adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arraylist_homedecor);
                 }

                 if (i==2)
                 {
                     subcategory_adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arraylist_food);
                 }

                 if (i==3)
                 {
                     subcategory_adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arraylist_artcraft);
                 }

                 if (i==4)
                 {
                     subcategory_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arraylist_accessories);
                 }
                 spinner_subcat.setAdapter( subcategory_adapter);


             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {


             }
         });


    }
}*/
