package com.example.wang_.ecommercev2.Server;

import com.example.wang_.ecommercev2.Adapter.MyProduct;
import com.example.wang_.ecommercev2.Adapter.SubEProduct;

import java.util.List;

public interface IServerManager extends IServerHelper{


    interface onResponseListener{
        void gotoCategory(String s);

        //void addSubEProduct(SubEProduct subEProduct);
    }

    interface onProductListener{

        void addProduct(MyProduct myProduct);
    }

    interface onSubProductListener{

        //void addSubProduct(List<SubEProduct> subEProductList);
        void addSubEProduct(SubEProduct subEProduct);
    }

}
