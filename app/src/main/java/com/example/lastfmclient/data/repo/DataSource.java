package com.example.lastfmclient.data.repo;

import com.example.lastfmclient.data.albumResults.Album;

import java.util.List;

import io.reactivex.Maybe;

public interface DataSource {
    //Can utilise Room's inbuilt Maybe Support - better than Flowable to handle empty state of DB
    Maybe<List<Album>> getAlbums(String albumName, int page);
}
