package com.example.miniproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.miniproject.R;
import com.example.miniproject.modals.News;

import java.util.List;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.NewsHolder> {
    private LayoutInflater inflater;
    private List<News> newsList;
    private NewsListener newsListener;

    public SavedAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    public void setNewsListener(NewsListener newsListener) {
        this.newsListener = newsListener;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_item_row, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        News news = newsList.get(position);
        holder.bindData(news);
        if (newsListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newsListener.onClick(news);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    newsListener.onLongClick(news,v);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title, description;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            img = itemView.findViewById(R.id.newsImage);
            title = itemView.findViewById(R.id.newsTitle);
            description = itemView.findViewById(R.id.newsDescription);
        }

        public void bindData(News news){
            title.setText(news.getTitle());
            description.setText(news.getDescription());
            Glide.with(img).load(news.getUrlToImage()).into(img);
        }
    }

    public interface NewsListener{
        void onClick(News news);
        void onLongClick(News news, View v);
    }
}


