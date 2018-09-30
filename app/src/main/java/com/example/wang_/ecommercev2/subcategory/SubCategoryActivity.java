package com.example.wang_.ecommercev2.subcategory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wang_.ecommercev2.Adapter.SubEProduct;
import com.example.wang_.ecommercev2.Adapter.SubProductAdapter;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.MyURL;
import com.example.wang_.ecommercev2.advertisement.MyAdvertisementActivity;
import com.example.wang_.ecommercev2.category.CategoryActivity;
import com.example.wang_.ecommercev2.orderhistory.OrderHistoryActivity;
import com.example.wang_.ecommercev2.productList.ProductActivity;
import com.example.wang_.ecommercev2.profile.ProfileActivity;
import com.example.wang_.ecommercev2.wishlist.WishListActivity;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryActivity extends AppCompatActivity implements IViewSubCategory{

    String id, appapikey, cid;
    IPresenterSubCategory presenter;
    List<SubEProduct> mylist;
    RecyclerView recyclerView_product;
    SubProductAdapter myAdapter;
    SharedPreferences.Editor editor;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        final SharedPreferences prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);
        presenter = new PresenterSubCategory(SubCategoryActivity.this);
        mylist = new ArrayList<>();
        recyclerView_product = findViewById(R.id.recyclerView_sub);
        editor = getSharedPreferences("ServerInfo", MODE_PRIVATE).edit();

        toolbar = findViewById(R.id.toolbar_sub);
        toolbar.setTitle("Subcategory");
        setSupportActionBar(toolbar);

        id = prefs.getString("id", "umm");
        appapikey = prefs.getString("appapikey", "umm");
        cid = prefs.getString("cid", "umm");

        myAdapter = new SubProductAdapter(mylist, new SubProductAdapter.SubEProductOnClickListener() {
            @Override
            public void onItemClick(SubEProduct subEProduct) {

                String scid = subEProduct.getScid();
                Intent i = new Intent(SubCategoryActivity.this, ProductActivity.class);
                //i.putExtra("scid", scid);
                editor.putString("scid",scid);
                editor.commit();
                startActivity(i);

            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(SubCategoryActivity.this);
        recyclerView_product.setLayoutManager(manager);
        recyclerView_product.setItemAnimator(new DefaultItemAnimator());
        recyclerView_product.setAdapter(myAdapter);


        String url_sub = MyURL.URL_SUBCATEGORY + "?Id=" + cid + "&api_key=" + appapikey +"&user_id=" + id;

        presenter.loadSub(url_sub);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.myprofile:
                Intent i1 = new Intent(SubCategoryActivity.this, ProfileActivity.class);
                startActivity(i1);
                break;
            case R.id.mywishlist:
                Intent i = new Intent(SubCategoryActivity.this, WishListActivity.class);
                startActivity(i);
                break;
            case R.id.order_history:
                Intent i2 = new Intent(SubCategoryActivity.this, OrderHistoryActivity.class);
                startActivity(i2);
                break;
            case R.id.myad:
                Intent i3 = new Intent(SubCategoryActivity.this, MyAdvertisementActivity.class);
                startActivity(i3);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void addSubEProduct(SubEProduct subEProduct) {
        mylist.add(subEProduct);
        myAdapter.notifyDataSetChanged();
    }
}
