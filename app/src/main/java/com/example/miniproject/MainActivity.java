package com.example.miniproject;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.miniproject.adapters.ViewPagerAdapter;
import com.example.miniproject.api.Api;
import com.example.miniproject.api.ApiBuilder;
import com.example.miniproject.modals.News;
import com.example.miniproject.modals.NewsResponse;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String TAG = "MainActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    // Fragment
    private NewsFragment newsFragment = new NewsFragment();
    private SavedFragment savedFragment = new SavedFragment();
    private FavoriteFragment favoriteFragment = new FavoriteFragment();
//    private NewsDetailFragment newsDetailFragment = new NewsDetailFragment();

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
//        initFragment();
    }

//    private void initFragment() {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.fragment_container, newsDetailFragment);
//        transaction.commit();
//    }

    private void initViews() {
        tabLayout = findViewById(R.id.tabsLayout);
        viewPager = findViewById(R.id.viewPager);
        toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        newsFragment = new NewsFragment();
        savedFragment = new SavedFragment();
        favoriteFragment = new FavoriteFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(newsFragment, "news");
        viewPagerAdapter.addFragment(savedFragment, "saved");
        viewPagerAdapter.addFragment(favoriteFragment, "favorite");
        viewPager.setAdapter(viewPagerAdapter);
    }

//    public void showFragments(Fragment fmShow) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//        transaction.replace(R.id.fragment_container, fmShow);
//        transaction.show(fmShow);
//        transaction.commit();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem search = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        if (query.isEmpty()){
            return false;
        }else {
            ApiBuilder.getInstance().getSearchedNews(query, "9e9fd7e3373343e8ad8252a96ae0d53a").enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    assert response.body() != null;
                    ArrayList<News> data = response.body().getNews();
                    newsFragment.setAdapterData(data);
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}