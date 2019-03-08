package com.example.lastfmclient.screens.albumDetails.di;

import android.arch.lifecycle.ViewModelProviders;

import com.example.lastfmclient.data.repo.DataSource;
import com.example.lastfmclient.data.repo.LastFMRepository;
import com.example.lastfmclient.di.Repository;
import com.example.lastfmclient.screens.albumDetails.AlbumDetailsActivity;
import com.example.lastfmclient.screens.albumDetails.AlbumDetailsViewModel;
import com.example.lastfmclient.screens.albumDetails.AlbumDetailsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumDetailsModule {
    @AlbumDetailsScope
    @Provides
    public AlbumDetailsViewModelFactory provideAlbumDetailsViewModelFactory(
            @Repository DataSource lastFMRepository) {
        return new AlbumDetailsViewModelFactory(lastFMRepository);
    }

    @AlbumDetailsScope
    @Provides
    public AlbumDetailsViewModel provideAlbumDetailsViewModel(AlbumDetailsActivity albumDetailsActivity,
                                                              AlbumDetailsViewModelFactory albumDetailsViewModelFactory) {
        return ViewModelProviders.of(albumDetailsActivity, albumDetailsViewModelFactory)
                .get(AlbumDetailsViewModel.class);
    }
}
