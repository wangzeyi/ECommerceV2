package com.example.wang_.ecommercev2.data;

import android.content.Context;

import com.example.wang_.ecommercev2.productList.IViewProduct;
import com.example.wang_.ecommercev2.productList.ProductActivity;

public class DBManager implements IDBManager {

    IViewProduct view;
    IDBHelper dbhelper;

    public DBManager(ProductActivity productActivity){
        this.view = productActivity;
        this.dbhelper = new DBHelper(productActivity);
    }


    @Override
    public void saveOrder(int userid, int itemid, int quantity, String image, String pname, IDBManager.onSaveListener listener) {
        dbhelper.saveOrder(userid, itemid, quantity, image, pname, listener);
    }
}
