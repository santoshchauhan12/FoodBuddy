package com.droidpro.foodbuddy.network;

import com.droidpro.foodbuddy.models.RestaurantCategories;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;

/**
 * Created by deepak on 19/4/17.
 * Molekule rest api end points
 */

public interface FoodBuddyRestApi {

    /*@NonNull
    @POST("write/api/v1/add/signup/user")
    Single<SignUpResp> signUp(@Body SignUpReq user);*/


    @GET("api/v2.1/categories")
    Single<RestaurantCategories> getRestaurantCategories(@Query("timeStamp") long timeStamp, @Query("count") int count);


}
