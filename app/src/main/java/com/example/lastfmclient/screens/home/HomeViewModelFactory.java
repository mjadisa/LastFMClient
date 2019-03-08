package com.example.lastfmclient.screens.home;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.lastfmclient.data.repo.LastFMRepository;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;
    private final LastFMRepository lastFMRepository;

    public HomeViewModelFactory(Application application, LastFMRepository lastFMRepository) {
        this.application = application;
        this.lastFMRepository = lastFMRepository;
    }


    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return  (T) new HomeViewModel(application, lastFMRepository);
        }
        throw new IllegalArgumentException("The class has to be an instance of: "
                + HomeViewModel.class.getSimpleName());
    }
}
