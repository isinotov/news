package com.example.isinotov.tinkoffnews.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.isinotov.tinkoffnews.R;

/**
 * Created by isinotov on 04/03/2016.
 */
public class DetailNewsFragment extends Fragment {
    TextView detail;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_news_fragment, container, false);
        detail = (TextView) rootView.findViewById(R.id.detail);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
