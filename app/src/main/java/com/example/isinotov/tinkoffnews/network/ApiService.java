package com.example.isinotov.tinkoffnews.network;

import com.example.isinotov.tinkoffnews.models.NewsDetails;
import com.example.isinotov.tinkoffnews.models.NewsItems;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by isinotov on 20/02/2016.
 */
public interface ApiService {
    @GET("news")
    Observable<NewsItems> getNews();

    @GET("news_content")
    Observable<NewsDetails> getDetailNews(@Query("id") long id);
}
