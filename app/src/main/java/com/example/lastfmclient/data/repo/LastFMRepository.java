package com.example.lastfmclient.data.repo;

import com.example.lastfmclient.data.albumDetails.AlbumDetails;
import com.example.lastfmclient.data.model.AlbumResults;

import io.reactivex.Maybe;

public class LastFMRepository implements DataSource {

    private final DataSource remoteDataSource;

    public LastFMRepository(DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Maybe<AlbumResults> getAlbums(String albumName, int page) {
        return remoteDataSource.getAlbums(albumName, page);
    }

    @Override
    public Maybe<AlbumDetails> getAlbumDetails(String albumName, String artistName) {
        return remoteDataSource.getAlbumDetails(albumName, artistName);
    }
}
