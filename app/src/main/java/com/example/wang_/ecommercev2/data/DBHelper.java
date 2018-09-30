package com.example.wang_.ecommercev2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.wang_.ecommercev2.Adapter.OrderProduct;
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
    public void saveOrder(String user_info, String p_info, IDBManager.onSaveListener listener) {
        Log.d("MyAd", user_info+" "+p_info);
        //                String p_info = pid+" "+pname+" "+pquantity+" "+prize;
        //                String user_info = id+" "+usernm+" "+mobile+" "+email+" "+appapikey;
        String[] user_info_split = user_info.split(" ");
        String[] p_info_split = p_info.split(" ");

        String itemid = p_info_split[0];
        String pname = p_info_split[1];
        String pquantity = p_info_split[2];
        String prize = p_info_split[3];
        String image = p_info_split[4];

        String userid = user_info_split[0];
        String usernm = user_info_split[1];
        String mobile = user_info_split[2];
        String email = user_info_split[3];
        String appapikey = user_info_split[4];

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
            values.put(Contract.Entry.COLUMN_NAME_QUANTITY, 1);
            values.put(Contract.Entry.COLUMN_NAME_IMAGE, image);
            values.put(Contract.Entry.COLUMN_NAME_PNAME, pname);
            values.put(Contract.Entry.COLUMN_NAME_PRIZE, prize);

            sqLiteDatabase.insert(Contract.Entry.TABLE_NAME, null, values);

            //String info = "" + userid + " " + itemid + " " + quantity;
        }
        //listener.addOrder(info);

    }

    @Override
    public void getOrder(String user_info, IDBManager.onCheckoutListener listener) {
        String[] user_info_split = user_info.split(" ");
        String userid = user_info_split[0];

        String whereClause = Contract.Entry.COLUMN_NAME_USERID +"=?";
        String[] whereArgs = new String[] {
                userid
        };
        //Log.d("MyOrder", userid);
        Cursor cursor = sqLiteDatabase.query(Contract.Entry.TABLE_NAME, null, whereClause, whereArgs,
                null, null, null);

        cursor.moveToFirst();
        do {
            String itemid = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME_ITEMID));
            int quantity = cursor.getInt(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME_QUANTITY));
            String pname = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME_PNAME));
            String prize = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME_PRIZE));

            String p_info = itemid+" "+ quantity+" "+pname+" "+prize;
            listener.placeOrder(user_info, p_info);


        } while (cursor.moveToNext());

    }

    @Override
    public void setProfilePic(String url, String id) {

        String whereClause = Contract.Entry.COLUMN_NAME_USERID + "=?";
        String[] whereArgs = new String[]{
            id
        };
        Cursor cursor = sqLiteDatabase.query(Contract.Entry.TABLE_NAME_PROFILE, null, whereClause,whereArgs,
                null, null, null);
        if(cursor.moveToFirst()){
            ContentValues values_update = new ContentValues();
            values_update.put(Contract.Entry.COLUMN_NAME_IMAGE, url);
            sqLiteDatabase.update(Contract.Entry.TABLE_NAME_PROFILE, values_update, whereClause, whereArgs);
        }
        else{
            ContentValues values = new ContentValues();
            values.put(Contract.Entry.COLUMN_NAME_IMAGE, url);
            values.put(Contract.Entry.COLUMN_NAME_USERID, id);
            sqLiteDatabase.insert(Contract.Entry.TABLE_NAME_PROFILE, null, values);
        }

    }

    @Override
    public String existProfile(String id) {

        String whereClause = Contract.Entry.COLUMN_NAME_USERID + "=?";
        String[] whereArgs = new String[]{
                id
        };
        Cursor cursor = sqLiteDatabase.query(Contract.Entry.TABLE_NAME_PROFILE, null, whereClause,whereArgs,
                null, null, null);

        if(cursor.moveToFirst()){
           String image = cursor.getString(cursor.getColumnIndex(Contract.Entry.COLUMN_NAME_IMAGE));

           return image;
        }
        else{
           return null;
        }
    }


}
