package com.example.wang_.ecommercev2.subcategory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.wang_.ecommercev2.Adapter.SubEProduct;
import com.example.wang_.ecommercev2.Adapter.SubProductAdapter;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.MyURL;
import com.example.wang_.ecommercev2.productList.ProductActivity;

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
    public void addSubEProduct(SubEProduct subEProduct) {
        mylist.add(subEProduct);
        myAdapter.notifyDataSetChanged();
    }
}
