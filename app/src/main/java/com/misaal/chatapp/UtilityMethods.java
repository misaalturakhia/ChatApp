package com.misaal.chatapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Misaal on 24/04/2015.
 */
public abstract class UtilityMethods {

    /**
     * Checks if the phone has any active network connection and returns true or false
     * @return
     */
    public static boolean isNetConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            return false;// There are no active networks.
        } else
            return true;
    }
}
