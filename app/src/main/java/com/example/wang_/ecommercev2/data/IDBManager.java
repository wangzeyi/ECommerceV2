package com.example.wang_.ecommercev2.data;

public interface IDBManager extends IDBHelper {


    interface onSaveListener{

    }

    interface onCheckoutListener{
         void placeOrder(String user_info, String p_info);
    }
}
