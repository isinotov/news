package com.example.isinotov.tinkoffnews.models;

import java.util.List;

/**
 * Created by isinotov on 08/03/2016.
 */
public class NewsDetailsResponse {
    String resultCode;
    NewsItemDetails payload;

    public NewsItemDetails getPayload() {
        return payload;
    }

    public String getResultCode() {
        return resultCode;
    }
}
