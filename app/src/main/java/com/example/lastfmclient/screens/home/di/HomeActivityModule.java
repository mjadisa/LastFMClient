package com.example.lastfmclient.screens.home.di;

import com.example.lastfmclient.screens.home.HomeActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class HomeActivityModule {
    @HomeScope
    @Binds
    abstract HomeActivity provideHomeActivity(HomeActivity homeActivity);
}
