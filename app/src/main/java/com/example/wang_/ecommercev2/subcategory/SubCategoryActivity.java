package com.example.wang_.ecommercev2.subcategory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wang_.ecommercev2.Adapter.EProduct;
import com.example.wang_.ecommercev2.Adapter.SubEProduct;
import com.example.wang_.ecommercev2.Adapter.SubProductAdapter;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.MyURL;
import com.example.wang_.ecommercev2.category.CategoryActivity;
import com.example.wang_.ecommercev2.productList.ProductActivity;
import com.example.wang_.ecommercev2.utils.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryActivity extends AppCompatActivity implements IViewSubCategory{

    String id, appapikey, cid;
    IPresenterSubCategory presenter;
    List<SubEProduct> mylist;
    RecyclerView recyclerView_product;
    SubProductAdapter myAdapter;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        final SharedPreferences prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);
        presenter = new PresenterSubCategory(SubCategoryActivity.this);
        mylist = new ArrayList<>();
        recyclerView_product = findViewById(R.id.recyclerView_sub);
        editor = getSharedPreferences("ServerInfo", MODE_PRIVATE).edit();


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
