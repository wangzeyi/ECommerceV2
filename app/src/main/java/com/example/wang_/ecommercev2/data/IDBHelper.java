package com.example.wang_.ecommercev2.data;

public interface IDBHelper {

    void saveOrder(int userid, int itemid, int quantity, String image, String pname, IDBManager.onSaveListener listener);

}
