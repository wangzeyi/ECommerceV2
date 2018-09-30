package com.example.wang_.ecommercev2.data;

import android.content.Context;

import com.example.wang_.ecommercev2.productList.IViewProduct;
import com.example.wang_.ecommercev2.productList.ProductActivity;

public class DBManager implements IDBManager{


    IDBHelper dbhelper;

    public DBManager(Context ctx){

        this.dbhelper = new DBHelper(ctx);
    }




    @Override
    public void saveOrder(String user_info, String p_info, IDBManager.onSaveListener listener) {
        dbhelper.saveOrder(user_info, p_info, listener);
    }

    @Override
    public void getOrder(String user_info, onCheckoutListener listener) {
        dbhelper.getOrder(user_info, listener);
    }

    @Override
    public void setProfilePic(String url, String id) {
        dbhelper.setProfilePic(url, id);
    }

    @Override
    public String existProfile(String id) {
        return dbhelper.existProfile(id);
    }


}
