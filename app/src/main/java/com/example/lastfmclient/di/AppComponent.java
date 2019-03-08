package com.example.lastfmclient.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {AndroidInjectionModule.class, NetworkModule.class, RepositoryModule.class})
@Singleton
public interface AppComponent {

}
