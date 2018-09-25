package com.example.wang_.ecommercev2.category;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wang_.ecommercev2.Adapter.EProduct;
import com.example.wang_.ecommercev2.Adapter.ProductAdapter;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.Server.MyURL;
import com.example.wang_.ecommercev2.Server.ServerHelper;
import com.example.wang_.ecommercev2.subcategory.SubCategoryActivity;
import com.example.wang_.ecommercev2.utils.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

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
            getFragmentManager().beginTransaction().add(R.id.category_layout, frag_category).commit();
        }
        else{
            frag_category = f;
            getFragmentManager().beginTransaction().replace(R.id.category_layout, f).commit();
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
}
