package com.example.wang_.ecommercev2.productList;

import android.view.View;

public interface IPresenterProduct {

    void loadProduct(String url);
    void saveOrder(int userid, int itemid, int quantity, String image, String pname);
    void onclick(View v);

}
