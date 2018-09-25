package com.example.wang_.ecommercev2.productList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.wang_.ecommercev2.Adapter.MyProduct;
import com.example.wang_.ecommercev2.Adapter.MyProductAdapter;
import com.example.wang_.ecommercev2.Adapter.OrderProduct;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.MyURL;
import com.example.wang_.ecommercev2.wishlist.WishListActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements IViewProduct{

    String id, appapikey, cid, scid;
    SharedPreferences prefs;
    IPresenterProduct presenter;
    List<MyProduct> mylist;
    MyProductAdapter myAdapter;
    RecyclerView recyclerView_product;
    List<OrderProduct> myOrderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);
        id = prefs.getString("id", "umm");
        appapikey = prefs.getString("appapikey", "umm");
        cid = prefs.getString("cid", "umm");
        scid = prefs.getString("scid", "umm");

        presenter = new PresenterProduct(ProductActivity.this);
        mylist = new ArrayList<>();
        recyclerView_product = findViewById(R.id.recyclerView_p);
        myAdapter = new MyProductAdapter(mylist, new MyProductAdapter.MyProductOnClickListener() {
            @Override
            public void onItemClick(MyProduct myProduct) {

                int pid = Integer.parseInt(myProduct.getId());
                int pquantity = Integer.parseInt(myProduct.getQuantity());
                String image = myProduct.getImage();
                String pname = myProduct.getPname();

                presenter.saveOrder(Integer.parseInt(id), pid, pquantity, image, pname);
            }
        });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(ProductActivity.this);
        recyclerView_product.setLayoutManager(manager);
        recyclerView_product.setItemAnimator(new DefaultItemAnimator());
        recyclerView_product.setAdapter(myAdapter);


        Log.d("MyProduct", id+" "+appapikey+" "+cid+" "+scid);

        //http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php?cid=107&scid=205
        // &api_key=7a89e02e90239a0fa2d17adb209ade18&user_id=1249

        String url_product = MyURL.URL_PRODUCT + "?cid=" + cid + "&scid=" + scid + "&api_key=" +
                appapikey + "&user_id=" + id;
        presenter.loadProduct(url_product);

        myOrderList = new ArrayList<>();


    }

    @Override
    public void addProduct(MyProduct myProduct) {
        mylist.add(myProduct);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void goWishList() {
        Intent i = new Intent(ProductActivity.this, WishListActivity.class);
        startActivity(i);
    }


    public void onClickProduct(View view) {
        presenter.onclick(view);
    }
}
