package com.example.wang_.ecommercev2.Server;

import com.example.wang_.ecommercev2.Adapter.MyProduct;
import com.example.wang_.ecommercev2.Adapter.SubEProduct;

import java.util.List;

public interface IServerManager extends IServerHelper{


    interface onResponseListener{
        void gotoCategory(String s);
        void loginSuccess();
        void loginFail();
        //void addSubEProduct(SubEProduct subEProduct);
    }

    interface onRegisterListener{
        void registerSuccess(String reg_info);
        void registerFail(String error_info);
    }

    interface onProductListener{

        void addProduct(MyProduct myProduct);
    }

    interface onSubProductListener{

        //void addSubProduct(List<SubEProduct> subEProductList);
        void addSubEProduct(SubEProduct subEProduct);
    }

    interface onCheckoutListener{

        void getOrderDetail(String order_info);
    }

    interface onOrderHistoryListener{
        void returnOrderHistory(String order_info);
    }


}
