package com.example.isinotov.tinkoffnews.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.isinotov.tinkoffnews.R;
import com.example.isinotov.tinkoffnews.models.NewsItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by isinotov on 04/03/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    private final NewsAdapterCallback mCallback;
    List<NewsItem> mData;

    public void setData(List<NewsItem> data) {
        Comparator<Long> comparator = Collections.reverseOrder();
        Collections.sort(data, (lhs, rhs) -> comparator.compare(lhs.getPublicationDate().getMilliseconds(), rhs.getPublicationDate().getMilliseconds()));
        mData = data;
    }


    public NewsAdapter(Activity callback) {
        try {
            this.mCallback = (NewsAdapterCallback) callback;
        } catch (ClassCastException e) {
            throw new ClassCastException("You have to implement NewsAdapterCallback");
        }
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        NewsItem newsItem = mData.get(position);
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(newsItem.getPublicationDate().getMilliseconds());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MMMM dd");
        holder.tvNewsTitle.setText(Html.fromHtml(newsItem.getText()));
        holder.tvDate.setText(dateFormat.format(calendar.getTime()));
        holder.itemView.setOnClickListener(v -> mCallback.onNewsClicked(newsItem.getId()));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvNewsTitle;
        TextView tvDate;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            tvNewsTitle = (TextView) itemView.findViewById(R.id.newsTitle);
            tvDate = (TextView) itemView.findViewById(R.id.date);
        }
    }


    public interface NewsAdapterCallback {
        void onNewsClicked(long newsId);
    }
}
