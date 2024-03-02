package com.example.e_udhyog;

public class User {

    private int id;
    private String name, phn_num, password;

    public User(int id, String name, String phn_num, String password) {
        this.id = id;
        this.name = name;
        this.phn_num = phn_num;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phn_num;
    }

    public String getPassword() {
        return password;
    }
}


