package com.example.lastfmclient.data.repo;

import com.example.lastfmclient.data.albumDetails.AlbumDetails;
import com.example.lastfmclient.data.albumResults.Album;
import com.example.lastfmclient.data.model.AlbumResults;

import java.util.List;

import io.reactivex.Maybe;

public interface DataSource {
    //Can utilise Room's inbuilt Maybe Support - better than Flowable to handle empty state of DB
    Maybe<AlbumResults> getAlbums(String albumName, int page);

    Maybe<AlbumDetails> getAlbumDetails(String albumName, String artistName);
}
