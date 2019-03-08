package com.example.lastfmclient.data.repo;

import com.example.lastfmclient.data.albumResults.Album;

import java.util.List;

import io.reactivex.Maybe;

public class LastFMRepository implements DataSource {

    private final DataSource remoteDataSource;

    public LastFMRepository(DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Maybe<List<Album>> getAlbums(String albumName, int page) {
        return remoteDataSource.getAlbums(albumName, page);
    }
}
