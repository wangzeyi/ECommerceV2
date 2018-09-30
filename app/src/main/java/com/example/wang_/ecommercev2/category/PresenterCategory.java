package com.example.wang_.ecommercev2.category;

import android.os.Bundle;
import android.view.View;

import com.example.wang_.ecommercev2.Adapter.EProduct;
import com.example.wang_.ecommercev2.R;
import com.example.wang_.ecommercev2.data.DBManager;
import com.example.wang_.ecommercev2.data.IDBManager;

public class PresenterCategory implements IPresenterCategory{

    IViewCategory view;
    IDBManager dbManager;

    public PresenterCategory(CategoryActivity categoryActivity) {
        view = categoryActivity;
        dbManager = new DBManager(categoryActivity);
    }


    @Override
    public void popDetail(EProduct eProduct) {
        //Log.d("MyListener", eProduct.getCid()+" "+eProduct.getCname() );
        Frag_Category frag = new Frag_Category();
        Bundle bundle = new Bundle();
        bundle.putString("cid", eProduct.getCid());
        bundle.putString("cname", eProduct.getCname());
        bundle.putString("cdiscription", eProduct.getCdiscription());
        bundle.putString("cimagerl",eProduct.getCimagerl());
        frag.setArguments(bundle);

        view.popDetail(frag);

    }

    @Override
    public void onClickHandler(View v) {

        switch (v.getId()){
            case R.id.button_back:
                view.destroyDetail();
                break;
            case R.id.button_more:
                view.goSubCategory();
                break;
            default:
                break;

        }
    }

    @Override
    public String existProfile(String id) {
        return dbManager.existProfile(id);
    }
}
