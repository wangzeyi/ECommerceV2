package com.example.wang_.ecommercev2.wishlist;


import com.example.wang_.ecommercev2.Adapter.OrderProduct;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class WishListDagger {

    @Provides
    public List<OrderProduct> provideList(){

        return new ArrayList<>();
    }

    @Provides
    public IPresenterWishList givePresenter(IViewWishList view){

        return new PresenterWishList((WishListActivity) view);
}

    @Provides
    public IViewWishList giveViewWishList(){
        return new IViewWishList() {
            @Override
            public void loadDataBase() {

            }
        };
    }

}
