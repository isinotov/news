package com.example.isinotov.tinkoffnews.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.isinotov.tinkoffnews.R;
import com.example.isinotov.tinkoffnews.adapters.NewsAdapter;
import com.example.isinotov.tinkoffnews.fragments.DetailNewsFragment;
import com.example.isinotov.tinkoffnews.fragments.ListNewsFragment;

/**
 * Created by isinotov on 04/03/2016.
 */
public class NewsActivity extends AppCompatActivity implements NewsAdapter.NewsAdapterCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getFragmentManager().beginTransaction().replace(R.id.container, new ListNewsFragment()).commit();
    }

    @Override
    public void onNewsClicked(long newsId) {
        getFragmentManager().beginTransaction().replace(R.id.container, DetailNewsFragment.newInstance(newsId)).addToBackStack(null).commit();
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
