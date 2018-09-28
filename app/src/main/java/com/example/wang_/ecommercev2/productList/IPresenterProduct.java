package com.example.wang_.ecommercev2.productList;

import android.view.View;

public interface IPresenterProduct {

    void loadProduct(String url);
    void saveOrder(String user_info, String p_info);
    void onclick(View v);

}
