package com.example.lastfmclient.screens.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.example.lastfmclient.R;
import com.example.lastfmclient.common.PaginationListener;
import com.example.lastfmclient.data.albumResults.Album;
import com.example.lastfmclient.databinding.ActivityHomeBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class HomeActivity extends AppCompatActivity implements OnAlbumSelectedListener,
        PaginationListener.PaginationStateListener {

    @Inject
    HomeViewModel homeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        ActivityHomeBinding activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        activityHomeBinding.setProgressVisibility(homeViewModel.getProgressObservable());

        AlbumsAdapter albumsAdapter = new AlbumsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.rvAlbums);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(albumsAdapter);
        recyclerView.addOnScrollListener(new PaginationListener(linearLayoutManager, this));
        homeViewModel.getAlbumsObservable().observe(this, albumsAdapter::setData);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_SEARCH)) {
            homeViewModel.getAlbums(intent.getStringExtra(SearchManager.QUERY));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return true;
    }


    @Override
    public void onAlbumSelected(Album album) {

    }

    @Override
    public boolean isLoading() {
        return homeViewModel.isLoading();
    }

    @Override
    public boolean isLastPage() {
        return homeViewModel.isLastPage();
    }

    @Override
    public void loadMoreItems() {
        homeViewModel.loadMoreAlbums();
    }
}
