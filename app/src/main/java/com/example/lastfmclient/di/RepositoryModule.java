package com.example.lastfmclient.di;

import com.example.lastfmclient.data.repo.DataSource;
import com.example.lastfmclient.data.repo.LastFMRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Repository
    @Singleton
    DataSource provideLastFMRepository(@Remote DataSource dataSource) {
        return new LastFMRepository(dataSource);
    }
}
