package com.example.lastfmclient.screens.home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.example.lastfmclient.data.albumResults.Album;
import com.example.lastfmclient.data.albumResults.AlbumResultsResponse;
import com.example.lastfmclient.data.repo.DataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Album>> albumsObservable;

    private final ObservableBoolean progressObservable;

    private final List<Album> albums;

    private final DataSource lastFMRepository;

    private final CompositeDisposable compositeDisposable;


    public HomeViewModel(@NonNull Application application, DataSource lastFMRepository) {
        super(application);
        this.lastFMRepository = lastFMRepository;
        albumsObservable  = new MutableLiveData<>();
        progressObservable = new ObservableBoolean(false);
        albums = new ArrayList<>();
        compositeDisposable = new CompositeDisposable();
    }

    public LiveData<List<Album>> getAlbumsObservable() {
        return albumsObservable;
    }

    public ObservableBoolean getProgressObservable() {
        return progressObservable;
    }


    private void getData(String albumName) {
        compositeDisposable.add(lastFMRepository.getAlbums(albumName, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(consumer -> progressObservable.set(true))
                .doOnEvent((success, failure) -> progressObservable.set(false))
                .subscribe(this::handleSuccess, this::handleError));
    }

    private void handleSuccess(List<Album> albums) {
        albumsObservable.setValue(albums);
    }

    private void handleError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
