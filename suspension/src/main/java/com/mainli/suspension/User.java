package com.mainli.suspension;

/**
 * Created by Mainli on 2016/11/9.
 */

public class User {
    private String name;
    private String phone;

    @Override
    public String toString() {
        return name + "," + phone;
    }

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

