package com.example.lastfmclient.di;

import com.example.lastfmclient.screens.albumDetails.AlbumDetailsActivity;
import com.example.lastfmclient.screens.albumDetails.di.AlbumDetailsModule;
import com.example.lastfmclient.screens.albumDetails.di.AlbumDetailsScope;
import com.example.lastfmclient.screens.home.HomeActivity;
import com.example.lastfmclient.screens.home.di.HomeModule;
import com.example.lastfmclient.screens.home.di.HomeScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector(modules = HomeModule.class)
    @HomeScope
    abstract HomeActivity homeActivity();

    @ContributesAndroidInjector(modules = AlbumDetailsModule.class)
    @AlbumDetailsScope
    abstract AlbumDetailsActivity albumDetailsActivity();
}
