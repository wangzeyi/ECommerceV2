package com.example.wang_.ecommercev2.checkout;

import android.util.Log;

import com.example.wang_.ecommercev2.Server.IServerManager;
import com.example.wang_.ecommercev2.Server.MyURL;
import com.example.wang_.ecommercev2.Server.ServerManager;
import com.example.wang_.ecommercev2.data.DBManager;
import com.example.wang_.ecommercev2.data.IDBManager;

public class PresenterCheckout implements IPresenterCheckout, IServerManager.onCheckoutListener, IDBManager.onCheckoutListener {

    IViewCheckout view;
    IServerManager manager;
    IDBManager dbManager;

    public PresenterCheckout(CheckoutActivity checkoutActivity) {
        this.view = checkoutActivity;
        dbManager = new DBManager(checkoutActivity);
        manager = new ServerManager();
    }

    @Override
    public void getOrder(String user_info) {
        dbManager.getOrder(user_info, this);
    }

    @Override
    public void getOrderDetail(String order_info) {
        Log.d("MyDetail", order_info);
        view.setOrderDetail(order_info);
    }

    @Override
    public void placeOrder(String user_info, String p_info) {
        Log.d("MyOrder", user_info+" "+p_info);
        String[] p_info_split = p_info.split(" ");
        //String p_info = itemid+" "+ quantity+" "+pname+" "+prize;
        String itemid = p_info_split[0];
        String quantity = p_info_split[1];
        String pname = p_info_split[2];
        String prize = p_info_split[3];
//user_info = userid+" "+firstnm+lastnm+" "+billingad+" "+deliverad+" "+mobile+" "+email+" "+appapikey;
        String[] user_info_split = user_info.split(" ");
        String userid = user_info_split[0];
        String name = user_info_split[1];
        String billing = user_info_split[2];
        String deliver = user_info_split[3];
        String mobile = user_info_split[4];
        String email = user_info_split[5];
        String appapikey = user_info_split[6];

        //http://rjtmobile.com/aamir/e-commerce/android-app/orders.php
        //http://rjtmobile.com/aamir/e-commerce/android-app/orders.php?&item_id=701&item_names=laptop&item_quantity=1
        // &final_price=100000&&api_key=7057bc8168b477b9420aeca3fda3c868&user_id=1217
        // &user_name=Aamir&billingadd=Noida&deliveryadd=Noida&mobile=55565454&email=aa@gmail.com
        String url_order = MyURL.URL_ORDER+"?&item_id="+itemid+"&item_names="+pname+"&item_quantity="
                           +quantity+"&final_price="+prize
                           +"&&api_key="+appapikey+"&user_id="+userid+"&user_name="+name
                           +"&billingadd="+billing+"deliveryadd="+deliver+"&mobile="+mobile+"&email="+email;
        manager.placeOrder(url_order,this);

    }
}
