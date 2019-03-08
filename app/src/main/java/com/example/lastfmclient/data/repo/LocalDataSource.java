package com.example.lastfmclient.data.repo;

import com.example.lastfmclient.data.albumResults.Album;

import java.util.List;

import io.reactivex.Maybe;

public class LocalDataSource implements DataSource  {
    //TODO Can be used to cache results to database in future
    @Override
    public Maybe<List<Album>> getAlbums(String albumName, int page) {
        //NO-OP
        return null;
    }
}
