package com.droidpro.foodbuddy;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by yml on 22/4/17.
 */

public class FoodBuddyApp extends Application {

    private static FoodBuddyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();

        Realm.setDefaultConfiguration(config);
    }


    public static FoodBuddyApp getInstance(){

       return instance;
    }
}
