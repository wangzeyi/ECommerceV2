package com.example.wang_.ecommercev2.wishlist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.wang_.ecommercev2.Adapter.OrderProduct;
import com.example.wang_.ecommercev2.Adapter.OrderProductAdapter;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.checkout.CheckoutActivity;
import com.example.wang_.ecommercev2.data.Contract;
import com.example.wang_.ecommercev2.data.database.MyDataBase;
import com.example.wang_.ecommercev2.subcategory.SubCategoryActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class WishListActivity extends AppCompatActivity implements IViewWishList{

    @Inject
    List<OrderProduct> mylist;
    RecyclerView recyclerView_product;
    OrderProductAdapter myAdapter;
    
    IPresenterWishList presenter;

    MyDataBase myDataBase;
    SQLiteDatabase sqLiteDatabase;
    Toolbar toolbar;

    MyInterface myInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        toolbar = findViewById(R.id.toolbar_wishlist);
        toolbar.setTitle("My WishList");
        setSupportActionBar(toolbar);

        presenter = new PresenterWishList(WishListActivity.this);

        myInterface = DaggerMyInterface.builder().wishListDagger(new WishListDagger()).build();
        myInterface.inject(this);

        //mylist = new ArrayList<>();
        myAdapter = new OrderProductAdapter(mylist, new OrderProductAdapter.MyOrderOnClickListener() {
            @Override
            public void onItemClick(OrderProduct orderProduct) {
               presenter.onClickWishList(orderProduct);
            }
        });

        recyclerView_product = findViewById(R.id.recyclerView_order);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(WishListActivity.this);
        recyclerView_product.setLayoutManager(manager);
        recyclerView_product.setItemAnimator(new DefaultItemAnimator());
        recyclerView_product.setAdapter(myAdapter);

        myDataBase = new MyDataBase(WishListActivity.this);
        sqLiteDatabase = myDataBase.getWritableDatabase();

        //String url = "http://cdn.journaldev.com/wp-content/uploads/2016/11/android-image-picker-project-structure.png";
        //mylist.add(new OrderProduct(1,1,1, "P1", url));
        //myAdapter.notifyDataSetChanged();

        loadDataBase();

    }


    @Override
    public void loadDataBase(){

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Contract.Entry.TABLE_NAME, null);
        if(cursor.moveToFirst()) {

            //myArrayList = new ArrayList<>();
            mylist.clear();
            myAdapter.notifyDataSetChanged();

            do {
                int userid = cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME_USERID));
                int itemid = cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME_ITEMID));
                int quantity = cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME_QUANTITY));
                String pname = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME_PNAME));
                String image = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME_IMAGE));

                //Toast.makeText(MainActivity.this, ""+cursor.getPosition(), Toast.LENGTH_SHORT).show();
                OrderProduct orderProduct = new OrderProduct(userid, itemid, quantity, pname, image);
                mylist.add(orderProduct);
                myAdapter.notifyDataSetChanged();

            } while (cursor.moveToNext());
        }
        else{
            mylist.clear();
            myAdapter.notifyDataSetChanged();
            Toast.makeText(this, "WishList is Empty", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wishlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.checkout:
                Intent i = new Intent(WishListActivity.this, CheckoutActivity.class);
                startActivity(i);
                break;
        }

        return true;
    }
}
