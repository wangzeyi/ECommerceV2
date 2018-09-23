package com.example.wang_.ecommercev2.subcategory;

import android.util.Log;

import com.example.wang_.ecommercev2.Adapter.SubEProduct;
import com.example.wang_.ecommercev2.Server.IServerManager;
import com.example.wang_.ecommercev2.Server.ServerManager;

import java.util.List;

public class PresenterSubCategory implements IPresenterSubCategory, IServerManager.onResponseListener{

    IViewSubCategory view;
    IServerManager manager;

    public PresenterSubCategory(SubCategoryActivity subCategoryActivity) {
        this.view = subCategoryActivity;
        this.manager = new ServerManager();
    }

    @Override
    public void gotoCategory(String s) {

    }

    @Override
    public void addSubEProduct(SubEProduct subEProduct) {
        view.addSubEProduct(subEProduct);
    }

    @Override
    public void loadSub(String url) {
        manager.loadSubCategory(url, this);
    }


    //http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php
    //http://rjtmobile.com/ansari/shopingcart/androidapp/cust_sub_category.php?Id=107&
    // api_key=3f846b9c6e6a16db4b91419af677be17&user_id=1393

}
