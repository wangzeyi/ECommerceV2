package com.example.wang_.ecommercev2.data;

public interface IDBHelper {

    void saveOrder(String user_info, String p_info, IDBManager.onSaveListener listener);
    void getOrder(String user_info, IDBManager.onCheckoutListener listener);
}
