package com.example.isinotov.tinkoffnews.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.isinotov.tinkoffnews.R;
import com.example.isinotov.tinkoffnews.adapters.NewsAdapter;
import com.example.isinotov.tinkoffnews.models.NewsItem;
import com.example.isinotov.tinkoffnews.models.NewsResponse;
import com.example.isinotov.tinkoffnews.network.RestClient;

import java.util.ArrayList;
import java.util.Collection;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by isinotov on 04/03/2016.
 */
public class ListNewsFragment extends Fragment {
    RecyclerView mRecyclerView;
    NewsAdapter mAdapter;
    Subscription subscription;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NewsAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this::refreshData);


        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getActivity()).build();
        // Create a new empty instance of Realm
        realm = Realm.getInstance(realmConfiguration);
        realm.beginTransaction();
        RealmResults<NewsItem> newsItems = realm.where(NewsItem.class).findAllSorted("publicationDate");
        realm.commitTransaction();
        mAdapter.setData(newsItems);
        mAdapter.notifyDataSetChanged();
        return rootView;
    }

    public void refreshData() {
        subscription = RestClient.getNewsService()
                .getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(NewsResponse::getPayload)
                .subscribe(newsItems -> {
                    realm.beginTransaction();
                    Collection<NewsItem> realmNews = realm.copyToRealm(newsItems);
                    realm.commitTransaction();
                    mAdapter.setData(new ArrayList<>(realmNews));
                    mAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                }, throwable -> {
                    Toast.makeText(getActivity(), R.string.error_happened, Toast.LENGTH_SHORT).show();
                    throwable.printStackTrace();
                    mSwipeRefreshLayout.setRefreshing(false);
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}