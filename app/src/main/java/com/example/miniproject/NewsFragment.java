package com.example.miniproject;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniproject.adapters.NewsAdapter;
import com.example.miniproject.api.ApiBuilder;
import com.example.miniproject.modals.News;
import com.example.miniproject.modals.NewsResponse;
import com.example.miniproject.room.Dao.AppDatabase;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment implements Callback<NewsResponse>, NewsAdapter.NewsListener{
    private final String TAG = "NewsFragment";
    private RecyclerView recyclerView;
    private TextView tv_nothing;
    private ProgressBar progressBar;

    private final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private FileManager fileManager = new FileManager();

    private NewsAdapter newsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.rc_list);
        tv_nothing = getActivity().findViewById(R.id.tv_nothing);
        progressBar = getActivity().findViewById(R.id.news_loading);
        newsAdapter = new NewsAdapter(getLayoutInflater());

        recyclerView.setHasFixedSize(true);
        newsAdapter.setNewsListener(this);
        recyclerView.setAdapter(newsAdapter);
        progressBar.setVisibility(View.VISIBLE);

        ApiBuilder.getInstance().getNews("us", "9e9fd7e3373343e8ad8252a96ae0d53a").enqueue(this);

    }

    public void setAdapterData(ArrayList<News> data){
        newsAdapter.setNewsList(data);
    }

    @Override
    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
        ArrayList<News> news = response.body().getNews();
        if (news.isEmpty()) {
            tv_nothing.setVisibility(View.VISIBLE);
        }
        progressBar.setVisibility(View.INVISIBLE);
        setAdapterData(news);
    }

    @Override
    public void onFailure(Call<NewsResponse> call, Throwable t) {
        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "error: " + t.getMessage());
    }

    @Override
    public void onClick(News news) {
        Intent intent = new Intent(getContext(), NewDetailsActivity.class);
        intent.putExtra("url", news.getUrl());
        startActivity(intent);
    }

    @Override
    public void onLongClick(News news, View v) {
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        popupMenu.inflate(R.menu.popup_item);
        Menu menu = popupMenu.getMenu();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.favoriteItem:
                        news.setSaved(false);
                        AppDatabase.getInstance(getContext()).getAppDao().insert(news);
                        Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.saveItem:
                        news.setSaved(true);
                        String path =  Environment.getExternalStorageDirectory().getPath() + "/save/" + System.currentTimeMillis() + ".html";
                        fileManager.download(news.getUrl(), path, new FileManager.FileDownloadCallBack() {
                            @Override
                            public void onSuccess(String path) {
                                news.setPathFile(path);
                                AppDatabase.getInstance(getContext()).getAppDao().insert(news);
                                getActivity().runOnUiThread(() ->  Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show());
                            }

                            @Override
                            public void onFailure(Exception e) {
                                Log.e(TAG, e.getMessage());
                            }
                        });
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }
}
