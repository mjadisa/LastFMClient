package com.example.lastfmclient.di;

import android.app.Application;

import com.example.lastfmclient.common.LastFMClient;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {AndroidInjectionModule.class, BuildersModule.class,
        NetworkModule.class, RepositoryModule.class})
@Singleton
public interface AppComponent {
    void inject(LastFMClient lastFMClient);

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder application(Application application);
    }
}
