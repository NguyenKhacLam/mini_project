package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniproject.adapters.FavoriteAdapter;
import com.example.miniproject.modals.News;
import com.example.miniproject.room.Dao.AppDatabase;

import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteAdapter.NewsListener {
    private final String TAG = "FavoriteFragment";

    private RecyclerView recyclerView;
    private TextView tvNothing;
    private FavoriteAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.rc_listFavor);
        tvNothing = getActivity().findViewById(R.id.tv_nothing2);

        recyclerView.setHasFixedSize(true);
        adapter = new FavoriteAdapter(getLayoutInflater());
        adapter.setNewsListener(this);
        recyclerView.setAdapter(adapter);

        loadData();
    }

    public void loadData() {
        List<News> news = AppDatabase.getInstance(getContext()).getAppDao().getNewsByType(false);
        if (news.size() == 0){
            tvNothing.setVisibility(View.VISIBLE);
        }
        adapter.setNewsList(news);
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
        popupMenu.inflate(R.menu.favorite_menu);
        Menu menu = popupMenu.getMenu();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.saveItem:
                        news.setSaved(true);
                        AppDatabase.getInstance(getContext()).getAppDao().update(news);
                        loadData();
                        break;
                    case R.id.deleteItem:
                        AppDatabase.getInstance(getContext()).getAppDao().delete(news);
                        loadData();
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }
}
