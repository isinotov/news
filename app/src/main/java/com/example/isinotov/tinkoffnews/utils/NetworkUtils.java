package com.example.isinotov.tinkoffnews.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by isinotov on 04/03/2016.
 */
public class NetworkUtils {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
