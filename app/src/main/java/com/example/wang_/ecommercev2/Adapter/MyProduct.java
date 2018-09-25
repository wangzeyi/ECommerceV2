package com.example.wang_.ecommercev2.Adapter;

public class MyProduct {
    /**
     * "id": "308",
     "pname": "i5-Laptop",
     "quantity": "1",
     "prize": "60000",
     "discription": "Online directory of electrical goods manufacturers, electronic goods suppliers and electronic product manufacturers. Get details of electronic products",
     "image": "https://rjtmobile.com/ansari/shopingcart/admin/uploads/product_images/images.jpg"
     *
     */
     String id, pname, quantity, prize, discription, image;

    public MyProduct(String id, String pname, String quantity, String prize, String discription, String image) {
        this.id = id;
        this.pname = pname;
        this.quantity = quantity;
        this.prize = prize;
        this.discription = discription;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getPname() {
        return pname;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrize() {
        return prize;
    }

    public String getDiscription() {
        return discription;
    }

    public String getImage() {
        return image;
    }
}
