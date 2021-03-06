package com.example.wang_.ecommercev2.category;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wang_.ecommercev2.Adapter.EProduct;
import com.example.wang_.ecommercev2.Adapter.ProductAdapter;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.MyURL;
import com.example.wang_.ecommercev2.Server.ServerHelper;
import com.example.wang_.ecommercev2.advertisement.MyAdvertisementActivity;
import com.example.wang_.ecommercev2.orderhistory.OrderHistoryActivity;
import com.example.wang_.ecommercev2.productList.ProductActivity;
import com.example.wang_.ecommercev2.profile.ProfileActivity;
import com.example.wang_.ecommercev2.subcategory.SubCategoryActivity;
import com.example.wang_.ecommercev2.utils.AppController;
import com.example.wang_.ecommercev2.wishlist.WishListActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements IViewCategory{
    String id, appapikey;
    ServerHelper serverHelper;
    List<EProduct> myList;
    RecyclerView recyclerView_product;
    ProductAdapter myAdapter;
    IPresenterCategory presenterCategory;
    Frag_Category frag_category;
    SharedPreferences.Editor editor;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        toolbar = findViewById(R.id.toolbar_category);
        toolbar.setTitle("Main Category");
        setSupportActionBar(toolbar);

        serverHelper = new ServerHelper();
        myList = new ArrayList<>();
        recyclerView_product = findViewById(R.id.recyclerView);
        presenterCategory = new PresenterCategory(CategoryActivity.this);
        editor = getSharedPreferences("ServerInfo", MODE_PRIVATE).edit();
        myAdapter = new ProductAdapter(myList, new ProductAdapter.EProductOnClickListener() {
            @Override
            public void onItemClick(EProduct eProduct) {
                //Log.d("MyListener", eProduct.getCid()+" "+eProduct.getCname() );
                editor.putString("cid",eProduct.getCid());
                editor.commit();
                //Log.d("MyCat", eProduct.getCid());
                presenterCategory.popDetail(eProduct);

            }
        });


        RecyclerView.LayoutManager manager = new LinearLayoutManager(CategoryActivity.this);
        recyclerView_product.setLayoutManager(manager);
        recyclerView_product.setItemAnimator(new DefaultItemAnimator());
        recyclerView_product.setAdapter(myAdapter);


        SharedPreferences prefs = getSharedPreferences("ServerInfo", MODE_PRIVATE);
        id = prefs.getString("id", "umm");
        appapikey = prefs.getString("appapikey", "umm");
        //Log.d("Tag2", id + " " + appapikey);

        serverHelper.setId(id);
        serverHelper.setAppapikey(appapikey);
//http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php
//http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?api_key=7057bc8168b477b9420aeca3fda3c868&user_id=1217

        loadCategory();



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
                Intent i1 = new Intent(CategoryActivity.this, ProfileActivity.class);
                startActivity(i1);
                break;
            case R.id.mywishlist:
                Intent i = new Intent(CategoryActivity.this, WishListActivity.class);
                startActivity(i);
                break;
            case R.id.order_history:
                Intent i2 = new Intent(CategoryActivity.this, OrderHistoryActivity.class);
                startActivity(i2);
                break;
            case R.id.myad:
                Intent i3 = new Intent(CategoryActivity.this, MyAdvertisementActivity.class);
                startActivity(i3);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void loadCategory(){

        String url_category = MyURL.URL_CATEGORY + "?" + "api_key=" + appapikey + "&user_id=" + id;


        JsonObjectRequest request = new JsonObjectRequest(url_category, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d("MyTag", response.toString());
                try {
                    JSONArray category = response.getJSONArray("category");
                    int len = category.length();
/**
 * "cid": "107",
 "cname": "Electronics",
 "cdiscription": "Online directory of electrical goods manufacturers, electronic goods suppliers and electronic product manufacturers. Get details of electronic products.",
 "cimagerl": "https://rjtmobile.com/ansari/shopingcart/admin/uploads/category_images/images.jpg"
 *
 */
                    for(int i=0; i<len; i++){
                        JSONObject product = category.getJSONObject(i);
                        String cid = product.getString("cid");
                        String cname = product.getString("cname");
                        String cdiscription = product.getString("cdiscription");
                        String cimagerl = product.getString("cimagerl");

                        //Log.d("Prod_Info", cid+" "+cname+" "+cdiscription+" "+cimagerl );
                        EProduct eProduct = new EProduct(cid, cname, cdiscription, cimagerl);
                        myList.add(eProduct);
                    }
                    myAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyTag", "Error Category");
            }
        });

        AppController.getInstance().addToRequestQueue(request);

    }

    @Override
    public void popDetail(Frag_Category f) {
        if(frag_category == null) {
            frag_category = f;
            getFragmentManager().beginTransaction().add(R.id.frag_display, frag_category).commit();
        }
        else{
            frag_category = f;
            getFragmentManager().beginTransaction().replace(R.id.frag_display, f).commit();
        }


    }

    @Override
    public void destroyDetail() {
        getFragmentManager().beginTransaction().remove(frag_category).commit();
    }

    @Override
    public void goSubCategory() {
        Intent i = new Intent(CategoryActivity.this, SubCategoryActivity.class);
        startActivity(i);
    }


    public void onClickFrag(View view) {

        presenterCategory.onClickHandler(view);

    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
