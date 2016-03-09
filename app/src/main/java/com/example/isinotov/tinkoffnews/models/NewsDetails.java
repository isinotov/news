package com.example.isinotov.tinkoffnews.models;

/**
 * Created by isinotov on 08/03/2016.
 */
public class NewsDetails {
    String resultCode;
    NewsDetail payload;

    public NewsDetail getPayload() {
        return payload;
    }

    public String getResultCode() {
        return resultCode;
    }
}
