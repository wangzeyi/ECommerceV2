package com.example.wang_.ecommercev2.Adapter;

public class SubEProduct {

/**
 "scid": "205",
 "scname": "Laptops",
 "scdiscription": "Laptop Prices in India - Buy Laptops online at best prices on ecom.com. Choose wide range of Branded Laptops .Free Shipping, Cash on delivery at India's .",
 "scimageurl": "https://rjtmobile.com/ansari/shopingcart/admin/uploads/sub_category_images/download.jpg"
 */

    String scid, scname, scdiscription, scimageurl;

    public SubEProduct(String scid, String scname, String scdiscription, String scimageurl) {
        this.scid = scid;
        this.scname = scname;
        this.scdiscription = scdiscription;
        this.scimageurl = scimageurl;
    }

    public String getScid() {
        return scid;
    }

    public String getScname() {
        return scname;
    }

    public String getScdiscription() {
        return scdiscription;
    }

    public String getScimageurl() {
        return scimageurl;
    }
}
