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
import com.example.isinotov.tinkoffnews.models.NewsItems;
import com.example.isinotov.tinkoffnews.network.RestClient;
import com.example.isinotov.tinkoffnews.utils.NetworkUtils;

import java.util.ArrayList;

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
    Subscription mSubscription;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private Realm mRealm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NewsAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this::refreshData);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getActivity()).build();
        mRealm = Realm.getInstance(realmConfiguration);

        loadData();
        return rootView;
    }


    public void loadData() {
        mRealm.beginTransaction();
        //Unfortunately realm is not supported sorting of objects
        RealmResults<NewsItem> newsItems = mRealm.where(NewsItem.class).findAll();
        mRealm.commitTransaction();
        mAdapter.setData(new ArrayList<>(newsItems));
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    public void refreshData() {
        mSubscription = RestClient.getNewsService()
                .getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(NewsItems::getPayload)
                .subscribe(newsItems -> {
                    mRealm.beginTransaction();
                    mRealm.clear(NewsItem.class);
                    mRealm.copyToRealm(newsItems);
                    mRealm.commitTransaction();
                    loadData();
                    mSwipeRefreshLayout.setRefreshing(false);
                }, throwable -> {
                    if (NetworkUtils.isNetworkAvailable(getActivity()))
                        Toast.makeText(getActivity(), R.string.error_happened, Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getActivity(), R.string.check_internet_connection, Toast.LENGTH_SHORT).show();
                    throwable.printStackTrace();
                    mSwipeRefreshLayout.setRefreshing(false);
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
