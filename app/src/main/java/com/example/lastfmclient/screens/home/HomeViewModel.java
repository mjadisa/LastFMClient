package com.example.lastfmclient.screens.home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.example.lastfmclient.common.Constants;
import com.example.lastfmclient.data.albumResults.Album;
import com.example.lastfmclient.data.model.AlbumResults;
import com.example.lastfmclient.data.repo.DataSource;
import com.example.lastfmclient.screens.albumDetails.AlbumDetailsActivity;

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

    private boolean isLoading;
    private boolean isLastPage;
    private int currentPage = 1;
    private String currentSearchQuery;


    public HomeViewModel(@NonNull Application application, DataSource lastFMRepository) {
        super(application);
        this.lastFMRepository = lastFMRepository;
        albumsObservable = new MutableLiveData<>();
        progressObservable = new ObservableBoolean(false);
        albums = new ArrayList<>();
        compositeDisposable = new CompositeDisposable();
        currentSearchQuery = "";
    }

    LiveData<List<Album>> getAlbumsObservable() {
        return albumsObservable;
    }

    ObservableBoolean getProgressObservable() {
        return progressObservable;
    }

    void getAlbums(String albumName) {
        currentSearchQuery = albumName;
        getData(currentSearchQuery, true);
    }

    boolean isLoading() {
        return isLoading;
    }

    boolean isLastPage() {
        return isLastPage;
    }

    void loadMoreAlbums() {
        getData(currentSearchQuery, false);
    }


    private void getData(String albumName, boolean isFreshQuery) {
        if (isFreshQuery) {
            resetState();
        }
        compositeDisposable.add(lastFMRepository.getAlbums(albumName, currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(consumer -> processLoadingStateChange(true))
                .doOnEvent((success, failure) -> processLoadingStateChange(false))
                .subscribe(this::handleSuccess, this::handleError));
    }


    private void handleSuccess(AlbumResults albumResults) {
        currentPage++;
        isLastPage = (albumResults.getStartIndex() + albumResults.getItemsPerPage())
                >= albumResults.getTotalResults();
        albums.addAll(albumResults.getAlbumList());
        albumsObservable.setValue(albums);
    }

    private void handleError(Throwable throwable) {
        throwable.printStackTrace();
    }

    private void resetState() {
        albums.clear();
        currentPage = 1;
        isLastPage = false;
        isLoading = false;
    }

    private void processLoadingStateChange(boolean isLoadingInProgress) {
        isLoading = isLoadingInProgress;
        progressObservable.set(isLoading);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (isLoading) {
            processLoadingStateChange(false);
        }
        compositeDisposable.clear();
    }
}
