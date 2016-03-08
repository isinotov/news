package com.example.isinotov.tinkoffnews.network;

import com.example.isinotov.tinkoffnews.models.NewsDetailsResponse;
import com.example.isinotov.tinkoffnews.models.NewsResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by isinotov on 20/02/2016.
 */
public interface ApiService {
    @GET("news")
    public Observable<NewsResponse> getNews();

    @GET("news_content")
    public Observable<NewsDetailsResponse> getDetailNews(@Query("id") long id);
}
