package com.example.lastfmclient.data.repo;

import com.example.lastfmclient.data.albumDetails.AlbumDetails;
import com.example.lastfmclient.data.model.AlbumResults;

import io.reactivex.Maybe;

public class LocalDataSource implements DataSource {
    //TODO Can be used to cache results to database in future
    @Override
    public Maybe<AlbumResults> getAlbums(String albumName, int page) {
        //NO-OP
        return null;
    }

    @Override
    public Maybe<AlbumDetails> getAlbumDetails(String albumName, String artistName) {
        //NO-OP
        return null;
    }
}
