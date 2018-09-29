package com.example.wang_.ecommercev2.advertisement;

import com.example.wang_.ecommercev2.data.DBManager;
import com.example.wang_.ecommercev2.data.IDBManager;

public class PresenterAdvertisement implements IPresenterAdvertisement, IDBManager.onSaveListener{

    IViewAdvertisement view;
    IDBManager dbManager;

    public PresenterAdvertisement(MyAdvertisementActivity myAdvertisementActivity) {
        this.view = myAdvertisementActivity;
        dbManager = new DBManager(myAdvertisementActivity);
    }


    @Override
    public void saveOrder(String user_info, String p_info) {
        dbManager.saveOrder(user_info, p_info, this);
    }
}
