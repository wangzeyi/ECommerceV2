package com.example.wang_.ecommercev2.productList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.wang_.ecommercev2.Adapter.MyProduct;
import com.example.wang_.ecommercev2.Adapter.MyProductAdapter;
import com.example.wang_.ecommercev2.Adapter.OrderProduct;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.MyURL;
import com.example.wang_.ecommercev2.advertisement.MyAdvertisementActivity;
import com.example.wang_.ecommercev2.category.CategoryActivity;
import com.example.wang_.ecommercev2.orderhistory.OrderHistoryActivity;
import com.example.wang_.ecommercev2.profile.ProfileActivity;
import com.example.wang_.ecommercev2.wishlist.WishListActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements IViewProduct{

    String id, appapikey, cid, scid, firstnm, lastnm, usernm, billingad, deliverad, mobile,email;
    SharedPreferences prefs;
    IPresenterProduct presenter;
    List<MyProduct> mylist;
    MyProductAdapter myAdapter;
    RecyclerView recyclerView_product;
    List<OrderProduct> myOrderList;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        toolbar = findViewById(R.id.toolbar_p);
        toolbar.setTitle("Product List");
        setSupportActionBar(toolbar);

        prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);
        id = prefs.getString("id", "umm");
        firstnm = prefs.getString("firstnm","umm");
        lastnm = prefs.getString("lastnm","umm");
        usernm = firstnm + " " + lastnm;
        appapikey = prefs.getString("appapikey", "umm");
        cid = prefs.getString("cid", "umm");
        scid = prefs.getString("scid", "umm");
        mobile = prefs.getString("mobile","umm");

        billingad = "billing address";
        deliverad = "deliver address";

        presenter = new PresenterProduct(ProductActivity.this);
        mylist = new ArrayList<>();
        recyclerView_product = findViewById(R.id.recyclerView_p);
        myAdapter = new MyProductAdapter(mylist, new MyProductAdapter.MyProductOnClickListener() {
            @Override
            public void onItemClick(MyProduct myProduct) {
/**
 * item_id'
 item_names
 item_quantity
 final_price

 user_id
 user_name
 billingadd
 deliveryadd
 mobile
 email

 api_key

 *
 */
                String pid = myProduct.getId();
                String pquantity = myProduct.getQuantity();
                String image = myProduct.getImage();
                String pname = myProduct.getPname();
                String prize = myProduct.getPrize();

                String p_info = pid+" "+pname+" "+pquantity+" "+prize+" "+image;
                String user_info = id+" "+usernm+" "+mobile+" "+email+" "+appapikey;

                presenter.saveOrder(user_info, p_info);
            }
        });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(ProductActivity.this);
        recyclerView_product.setLayoutManager(manager);
        recyclerView_product.setItemAnimator(new DefaultItemAnimator());
        recyclerView_product.setAdapter(myAdapter);


        //Log.d("MyProduct", id+" "+appapikey+" "+cid+" "+scid);

        //http://rjtmobile.com/ansari/shopingcart/androidapp/product_details.php?cid=107&scid=205
        // &api_key=7a89e02e90239a0fa2d17adb209ade18&user_id=1249

        String url_product = MyURL.URL_PRODUCT + "?cid=" + cid + "&scid=" + scid + "&api_key=" +
                appapikey + "&user_id=" + id;
        presenter.loadProduct(url_product);

        myOrderList = new ArrayList<>();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.myprofile:
                Intent i1 = new Intent(ProductActivity.this, ProfileActivity.class);
                startActivity(i1);
                break;
            case R.id.mywishlist:
                Intent i2 = new Intent(ProductActivity.this, WishListActivity.class);
                startActivity(i2);
                break;
            case R.id.order_history:
                Intent i3 = new Intent(ProductActivity.this, OrderHistoryActivity.class);
                startActivity(i3);
                break;
            case R.id.myad:
                Intent i4 = new Intent(ProductActivity.this, MyAdvertisementActivity.class);
                startActivity(i4);
                break;

        }

        return super.onOptionsItemSelected(item);
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
