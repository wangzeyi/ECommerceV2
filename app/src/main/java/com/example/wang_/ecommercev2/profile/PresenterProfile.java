package com.example.wang_.ecommercev2.profile;

import com.example.wang_.ecommercev2.data.DBManager;
import com.example.wang_.ecommercev2.data.IDBManager;

public class PresenterProfile implements IPresenterProfile{

    IViewProfile view;
    IDBManager dbmanager;

    public PresenterProfile(ProfileActivity profileActivity) {
        this.view = profileActivity;
        this.dbmanager = new DBManager(profileActivity);
    }


    @Override
    public void setProfilePic(String url, String id) {
        dbmanager.setProfilePic(url,id);
    }

    @Override
    public String existProfile(String id) {
        return dbmanager.existProfile(id);
    }
}
