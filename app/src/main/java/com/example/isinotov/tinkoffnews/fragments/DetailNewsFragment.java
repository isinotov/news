package com.example.isinotov.tinkoffnews.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.isinotov.tinkoffnews.R;
import com.example.isinotov.tinkoffnews.network.RestClient;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by isinotov on 04/03/2016.
 */
public class DetailNewsFragment extends Fragment {
    private static final String ARG_NEWS_ID = "NEWS_ID";
    TextView mTextViewDetail;
    long mNewsId;
    Subscription subscription;

    public static DetailNewsFragment newInstance(long newsId) {
        Bundle args = new Bundle();
        args.putLong(ARG_NEWS_ID, newsId);
        DetailNewsFragment fragment = new DetailNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsId = getArguments().getLong(ARG_NEWS_ID);
    }

    @Override
    public void onResume() {
        super.onResume();
        subscription = RestClient.getNewsService()
                .getDetailNews(mNewsId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(newsItemDetails -> {
                    mTextViewDetail.setText(Html.fromHtml(newsItemDetails.getPayload().getContent()));
                });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_news_fragment, container, false);
        mTextViewDetail = (TextView) rootView.findViewById(R.id.detail);
        mTextViewDetail.setMovementMethod(LinkMovementMethod.getInstance());
        return rootView;
    }
}
