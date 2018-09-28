package com.example.wang_.ecommercev2.data;

import android.provider.BaseColumns;

public class Contract {


   public static abstract class Entry implements BaseColumns{

       public static final String TABLE_NAME = "shoppingcart";
       public static final String COLUMN_NAME_USERID = "userid";
       public static final String COLUMN_NAME_ITEMID = "itemid";
       public static final String COLUMN_NAME_QUANTITY= "quantity";
       public static final String COLUMN_NAME_IMAGE = "image";
       public static final String COLUMN_NAME_PNAME = "pname";
       public static final String COLUMN_NAME_PRIZE = "prize";

   }

}
