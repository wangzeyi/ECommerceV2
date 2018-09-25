package com.example.wang_.ecommercev2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

        String whereClause = Contract.Entry.COLUMN_NAME_PNAME +"=?";
        String[] whereArgs = new String[] {
                pname
        };

        Cursor cursor = sqLiteDatabase.query(Contract.Entry.TABLE_NAME, null, whereClause, whereArgs,
                null, null, null);

        if(cursor.moveToFirst()) {

           int q = cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME_QUANTITY)) + 1;
           ContentValues values_update = new ContentValues();
           values_update.put(Contract.Entry.COLUMN_NAME_QUANTITY, q);
           sqLiteDatabase.update(Contract.Entry.TABLE_NAME, values_update, whereClause, whereArgs);
        }
        else {
            ContentValues values = new ContentValues();
            values.put(Contract.Entry.COLUMN_NAME_USERID, userid);
            values.put(Contract.Entry.COLUMN_NAME_ITEMID, itemid);
            values.put(Contract.Entry.COLUMN_NAME_QUANTITY, quantity);
            values.put(Contract.Entry.COLUMN_NAME_IMAGE, image);
            values.put(Contract.Entry.COLUMN_NAME_PNAME, pname);

            sqLiteDatabase.insert(Contract.Entry.TABLE_NAME, null, values);

            String info = "" + userid + " " + itemid + " " + quantity;
        }
        //listener.addOrder(info);

    }




}
