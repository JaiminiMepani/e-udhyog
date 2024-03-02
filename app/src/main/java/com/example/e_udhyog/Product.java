package com.example.e_udhyog;


public class Product {
    private String p_id;
    private String p_name;
    private String p_details;
    private String p_image;
    private String other_details;
    private String min_quantity;
    private String delivery_days;
    private String p_quantity;
    private String p_price;
    private String storename;
    private String address;
    private String state;


    public Product(String p_name, String p_quantity){
    this.p_name = p_name;
    this.p_quantity = p_quantity;
}


    public Product (String p_id, String p_image, String p_name, String p_price, String p_details,
                    String other_details, String min_quantity,String delivery_days, String address, String state, String storename)
    {
        this.p_id = p_id;
        this.p_image = p_image;
        this.p_name = p_name;
        this.p_details= p_details;
        this.p_price = p_price;
        this.other_details = other_details;
        this.min_quantity = min_quantity;
        this.delivery_days= delivery_days;
        this.address=address;
        this.state=state;
        this.storename=storename;
    }

    public Product (String p_id, String p_image, String p_name, String p_price, String p_details,
                    String other_details, String min_quantity) {
        this.p_id = p_id;
        this.p_image = p_image;
        this.p_name = p_name;
        this.p_details = p_details;
        this.p_price = p_price;
        this.other_details = other_details;
        this.min_quantity = min_quantity;

    }

    public String getId() {
        return p_id;
    }

    public String getP_Image() {
        return p_image;
    }

    public void setImage(String p_image) {
        this.p_image = p_image;
    }

    public String getP_Name() {
        return p_name;
    }

    public void setTitle(String p_name ){
        this.p_name = p_name;
    }


    public String  getP_Price() {
        return p_price;
    }

    public void setPrice(String  p_price ){
        this.p_price = p_price;
    }

    public String getP_Details() {
        return p_details;
    }

    public void setRating(String p_details) {
        this.p_details = p_details;
    }
    public  String getOtherdetails() {
        return other_details;
    }
    public void setOther_details(String other_details) {
        this.other_details = other_details;
    }

    public String getMin_quantity() {
        return min_quantity;
    }

    public void setMin_quantity(String min_quantity){
        this.min_quantity =min_quantity;
    }

    public String getDelivery_days() {
        return delivery_days;
    }

    public void setDelivery_days(String delivery_days){
        this.delivery_days = delivery_days;
    }
    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public  String getP_quantity() {
        return p_quantity;
    }

    public void setP_quantity(String p_quantity) {
        this.p_quantity = p_quantity;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
