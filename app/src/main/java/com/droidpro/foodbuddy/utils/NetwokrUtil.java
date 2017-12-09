package com.droidpro.foodbuddy.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.droidpro.foodbuddy.FoodBuddyApp;

/**
 * Created by deepak on 19/4/17.
 * All network related util method
 */

public class NetwokrUtil {

    /**
     * Method using checking internet connection availability
     * @return true if connection is available or false
     */
    public static boolean isInternetAvailable() {
        boolean stat = false;
        Context context = FoodBuddyApp.getInstance();
        if(null != context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(null != connectivityManager) {
                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                if (activeNetwork != null && activeNetwork.isAvailable() && activeNetwork.isConnected()) {
                   stat = true;
                }
            }

        }
        return stat;
    }
}
