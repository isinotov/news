package com.example.isinotov.tinkoffnews.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.isinotov.tinkoffnews.R;
import com.example.isinotov.tinkoffnews.fragments.ListNewsFragment;

/**
 * Created by isinotov on 04/03/2016.
 */
public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getFragmentManager().beginTransaction().replace(R.id.container, new ListNewsFragment()).commit();
    }
}
