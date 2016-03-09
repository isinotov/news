package com.example.isinotov.tinkoffnews.models;


import io.realm.RealmObject;

/**
 * Created by isinotov on 04/03/2016.
 */
public class DateHolder extends RealmObject {
    private long milliseconds;

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }
}
