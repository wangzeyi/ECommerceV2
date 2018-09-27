package com.example.wang_.ecommercev2.wishlist;

import android.database.sqlite.SQLiteDatabase;

import com.example.wang_.ecommercev2.Adapter.OrderProduct;
import com.example.wang_.ecommercev2.data.Contract;
import com.example.wang_.ecommercev2.data.database.MyDataBase;

import javax.inject.Inject;

public class PresenterWishList implements IPresenterWishList {

    IViewWishList view;
    MyDataBase myDataBase;
    SQLiteDatabase sqLiteDatabase;


    public PresenterWishList(WishListActivity wishListActivity) {
        this.view = wishListActivity;
        myDataBase = new MyDataBase(wishListActivity);
        sqLiteDatabase = myDataBase.getWritableDatabase();
    }

    @Override
    public void onClickWishList(OrderProduct orderProduct) {

        int itemid = orderProduct.getItemid();
        String pname = orderProduct.getItem_name();
        sqLiteDatabase.delete(Contract.Entry.TABLE_NAME,
                 Contract.Entry.COLUMN_NAME_PNAME + "=" +"'"+pname +"'",
                 null);
        view.loadDataBase();

    }
}
