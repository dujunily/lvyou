package com.bishe.me.bean;

public class ListBean {

    int price;
    String name;
    String des;

    public ListBean(int price, String name, String des) {
        this.price = price;
        this.name = name;
        this.des = des;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
