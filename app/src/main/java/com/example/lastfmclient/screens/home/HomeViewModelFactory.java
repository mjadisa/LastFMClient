package com.example.lastfmclient.screens.home;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.lastfmclient.data.repo.DataSource;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private final DataSource lastFMRepository;

    public HomeViewModelFactory(DataSource lastFMRepository) {
        this.lastFMRepository = lastFMRepository;
    }


    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(lastFMRepository);
        }
        throw new IllegalArgumentException("The class has to be an instance of: "
                + HomeViewModel.class.getSimpleName());
    }
}
