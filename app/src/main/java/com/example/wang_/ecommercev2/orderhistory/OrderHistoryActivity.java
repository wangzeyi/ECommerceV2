package com.example.wang_.ecommercev2.orderhistory;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.wang_.ecommercev2.Adapter.MyOrderHistory;
import com.example.wang_.ecommercev2.Adapter.MyOrderHistoryAdapter;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.MyURL;
import com.example.wang_.ecommercev2.main.IPresenter;
import com.example.wang_.ecommercev2.wishlist.WishListActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity implements IViewOrderHistory{

    IPresenterOrderHistory presenter;
    SharedPreferences prefs;
    List<MyOrderHistory> mylist;
    RecyclerView recyclerView_history;
    MyOrderHistoryAdapter myAdapter;
    ImageView imageView_flip;
    Animation anim_flip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        presenter = new PresenterOrderHistory(this);
        prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);
        mylist = new ArrayList<>();
        myAdapter = new MyOrderHistoryAdapter(mylist);
        imageView_flip = findViewById(R.id.imageView_flip);

        recyclerView_history = findViewById(R.id.recycler_history);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(OrderHistoryActivity.this);
        recyclerView_history.setLayoutManager(manager);
        recyclerView_history.setItemAnimator(new DefaultItemAnimator());
        recyclerView_history.setAdapter(myAdapter);

        //http://rjtmobile.com/aamir/e-commerce/android-app/order_history.php?api_key=7057bc8168b477b9420aeca3fda3c868
        //&user_id=1217&mobile=55565454
        String appapikey =  prefs.getString("appapikey", "umm");
        String userid = prefs.getString("id", "umm");
        String mobile = prefs.getString("mobile","umm");

        String url_orderhistory = MyURL.URL_ORDERHISTORY+"?api_key="+appapikey+"&user_id="+userid+"&mobile="+mobile;
        presenter.loadOrderHistory(url_orderhistory);

        anim_flip = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);

        imageView_flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(mylist);
                myAdapter.notifyDataSetChanged();
                imageView_flip.startAnimation(anim_flip);
            }
        });

    }

    @Override
    public void returnOderHistory(String order_info) {
        Log.d("MyOrderHist", order_info);
        MyOrderHistory myOrderHistory = new MyOrderHistory(order_info);
        mylist.add(0, myOrderHistory);
        myAdapter.notifyDataSetChanged();
    }
}
