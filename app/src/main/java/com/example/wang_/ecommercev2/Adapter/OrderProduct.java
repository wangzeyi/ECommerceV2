package com.example.wang_.ecommercev2.Adapter;

public class OrderProduct {

    int userid, itemid, quantity;
    String item_name, image;

    public OrderProduct(int userid, int itemid, int quantity, String item_name, String image) {
        this.userid = userid;
        this.itemid = itemid;
        this.quantity = quantity;
        this.item_name = item_name;
        this.image = image;
    }

    public int getUserid() {
        return userid;
    }

    public int getItemid() {
        return itemid;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getImage() {
        return image;
    }
}
