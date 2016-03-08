package com.example.isinotov.tinkoffnews;

import android.app.Application;
import android.content.Context;

/**
 * Created by isinotov on 04/03/2016.
 */
public class ApplicationWrapper extends Application {
    static Context context;


    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        context = null;
    }
}
