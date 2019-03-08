package com.example.lastfmclient.screens.albumDetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.example.lastfmclient.data.albumDetails.Album;
import com.example.lastfmclient.data.albumDetails.AlbumDetails;
import com.example.lastfmclient.data.repo.DataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AlbumDetailsViewModel extends ViewModel {

    private final MutableLiveData<Album> albumDetailsObservable;
    private final CompositeDisposable compositeDisposable;
    private final DataSource lastFMRepository;
    private final ObservableBoolean progressObservable;

    public AlbumDetailsViewModel(DataSource lastFMRepository) {
        this.lastFMRepository = lastFMRepository;
        albumDetailsObservable = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
        progressObservable = new ObservableBoolean(false);
    }

    LiveData<Album> getAlbumDetailsObservable() {
        return albumDetailsObservable;
    }

    ObservableBoolean getProgressObservable() {
        return progressObservable;
    }

    void getAlbumDetails(String albumName, String albumArtist) {
        compositeDisposable.add(lastFMRepository.getAlbumDetails(albumName, albumArtist)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe(disposable -> progressObservable.set(true))
        .doOnEvent((success, failure) -> progressObservable.set(false))
        .subscribe(this::handleSuccess, this::handleFailure));
    }

    private void handleSuccess(AlbumDetails albumDetails) {
        albumDetailsObservable.setValue(albumDetails.getAlbum());
    }

    private void handleFailure(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
