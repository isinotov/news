package com.example.isinotov.tinkoffnews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.isinotov.tinkoffnews.R;
import com.example.isinotov.tinkoffnews.models.NewsItem;

import java.util.List;

/**
 * Created by isinotov on 04/03/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    List<NewsItem> mData;

    public void setData(List<NewsItem> data) {
        mData = data;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        holder.newsTitle.setText(mData.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.newsTitle);
        }
    }
}
