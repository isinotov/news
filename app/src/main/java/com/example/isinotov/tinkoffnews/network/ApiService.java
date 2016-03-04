package com.example.isinotov.tinkoffnews.network;

import com.example.isinotov.tinkoffnews.models.NewsItem;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by isinotov on 20/02/2016.
 */
public interface ApiService {
    @GET("news")
    public Observable<List<NewsItem>> getNews();
}
