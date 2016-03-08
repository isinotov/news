package com.example.isinotov.tinkoffnews.network;

import com.example.isinotov.tinkoffnews.models.NewsItem;
import com.example.isinotov.tinkoffnews.models.NewsResponse;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by isinotov on 20/02/2016.
 */
public interface ApiService {
    @GET("news")
    public Observable<NewsResponse> getNews();

    @GET("news_content")
    public Observable<NewsResponse> getDetailNews(@Path("id") long id);
}
