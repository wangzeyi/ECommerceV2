package com.example.wang_.ecommercev2.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.wang_.ecommercev2.data.Contract;

public class MyDataBase extends SQLiteOpenHelper{

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Contract.Entry.TABLE_NAME + " (" +
                    Contract.Entry._ID + " INTEGER PRIMARY KEY," +
                    Contract.Entry.COLUMN_NAME_USERID + TEXT_TYPE + COMMA_SEP +
                    Contract.Entry.COLUMN_NAME_ITEMID + TEXT_TYPE + COMMA_SEP +
                    Contract.Entry.COLUMN_NAME_QUANTITY + INTEGER_TYPE + COMMA_SEP +
                    Contract.Entry.COLUMN_NAME_IMAGE + TEXT_TYPE + COMMA_SEP +
                    Contract.Entry.COLUMN_NAME_PNAME + TEXT_TYPE + COMMA_SEP +
                    Contract.Entry.COLUMN_NAME_PRIZE + TEXT_TYPE + " )";



    public MyDataBase(Context context) {
        super(context, "ECommerceDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
