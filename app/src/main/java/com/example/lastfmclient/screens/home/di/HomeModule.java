package com.example.lastfmclient.screens.home.di;

import android.arch.lifecycle.ViewModelProviders;

import com.example.lastfmclient.data.repo.DataSource;
import com.example.lastfmclient.di.Repository;
import com.example.lastfmclient.screens.home.HomeActivity;
import com.example.lastfmclient.screens.home.HomeViewModel;
import com.example.lastfmclient.screens.home.HomeViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {
        @HomeScope
        @Provides
        public HomeViewModelFactory provideHomeViewModelFactory(
                @Repository DataSource lastFMRepository) {
            return new HomeViewModelFactory(lastFMRepository);
        }

        @HomeScope
        @Provides
        public HomeViewModel provideHomeViewModel(HomeActivity homeActivity,
                                                  HomeViewModelFactory homeViewModelFactory) {
            return ViewModelProviders.of(homeActivity, homeViewModelFactory).get(HomeViewModel.class);
        }
}

