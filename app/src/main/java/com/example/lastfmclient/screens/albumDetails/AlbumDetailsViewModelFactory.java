package com.example.lastfmclient.screens.albumDetails;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.lastfmclient.data.repo.DataSource;

public class AlbumDetailsViewModelFactory implements ViewModelProvider.Factory {
    private final DataSource lastFMRepository;

    public AlbumDetailsViewModelFactory(DataSource lastFMRepository) {
        this.lastFMRepository = lastFMRepository;
    }


    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AlbumDetailsViewModel.class)) {
            return (T) new AlbumDetailsViewModel(lastFMRepository);
        }
        throw new IllegalArgumentException("The class has to be an instance of: "
                + AlbumDetailsViewModel.class.getSimpleName());
    }
}
