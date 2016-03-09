package com.example.isinotov.tinkoffnews.models;

import java.util.List;

/**
 * Created by isinotov on 04/03/2016.
 */
public class NewsItems {
    String resultCode;
    List<NewsItem> payload;

    public List<NewsItem> getPayload() {
        return payload;
    }

    public String getResultCode() {
        return resultCode;
    }
}
