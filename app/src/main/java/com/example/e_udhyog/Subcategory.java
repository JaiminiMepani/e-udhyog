package com.example.e_udhyog;

public class Subcategory
{
   private  String subcat_name,subcat_id;


    public Subcategory(){}
    public Subcategory(String subcat_id, String subcat_name) {
        this.subcat_id = subcat_id;
        this.subcat_name = subcat_name;
    }


    public Subcategory(String subcat_name) {

        this.subcat_name = subcat_name;
    }
    public String getSubcat_name() {
        return subcat_name;
    }

    public void setSubcat_name(String subcat_name) {
        this.subcat_name = subcat_name;
    }


    public String getSubcat_id() {
        return subcat_id;
    }

    public void setSubcat_id(String subcat_id) {
        this.subcat_id = subcat_id;
    }
}
