package com.example.lastfmclient.screens.albumDetails.di;

import com.example.lastfmclient.screens.albumDetails.AlbumDetailsActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AlbumDetailsActivityModule {
    @AlbumDetailsScope
    @Binds
    abstract AlbumDetailsActivity provideAlbumDetailsActivity(AlbumDetailsActivity albumDetailsActivity);
}
