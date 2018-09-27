package com.example.wang_.ecommercev2.wishlist;


import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {WishListDagger.class})
@Singleton
public interface MyInterface {

    void inject(WishListActivity wishListActivity);

}
