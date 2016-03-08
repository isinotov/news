package com.example.isinotov.tinkoffnews.models;

import android.support.annotation.NonNull;

import io.realm.RealmObject;

/**
 * Created by isinotov on 04/03/2016.
 */
public class PublicationDate extends RealmObject implements Comparable {
    private long milliseconds;

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    @Override
    public int compareTo(@NonNull Object another) {
        if (another instanceof PublicationDate)
            return (int) (milliseconds - ((PublicationDate) another).milliseconds);
        else
            return 0;
    }
}
