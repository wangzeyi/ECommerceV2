package com.example.wang_.ecommercev2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.wang_.ecommercev2.data.database.MyDataBase;

public class DBHelper implements IDBHelper{

    Context context;
    MyDataBase myDataBase;
    SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context ctx) {
        this.context = ctx;
        myDataBase = new MyDataBase(context);
        sqLiteDatabase = myDataBase.getWritableDatabase();
    }

    @Override
    public void saveOrder(int userid, int itemid, int quantity, String image, String pname, IDBManager.onSaveListener listener) {

        Log.d("DBHelper", ""+ userid+" "+ itemid+" "+quantity+" "+pname);

        ContentValues values = new ContentValues();
        values.put(Contract.Entry.COLUMN_NAME_USERID, userid);
        values.put(Contract.Entry.COLUMN_NAME_ITEMID, itemid);
        values.put(Contract.Entry.COLUMN_NAME_QUANTITY, quantity);
        values.put(Contract.Entry.COLUMN_NAME_IMAGE, image);
        values.put(Contract.Entry.COLUMN_NAME_PNAME, pname);

        sqLiteDatabase.insert(Contract.Entry.TABLE_NAME, null, values);

        String info = ""+ userid+" "+ itemid+" "+quantity;

        //listener.addOrder(info);

    }




}
