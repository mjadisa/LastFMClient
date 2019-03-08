package com.example.lastfmclient;

import com.example.lastfmclient.data.albumResults.Album;
import com.example.lastfmclient.data.model.AlbumResults;

import java.util.Collections;
import java.util.List;

class TestUtils {
    static AlbumResults generateAlbumSearchResults() {
        List<Album> albumList = Collections.singletonList(new Album());
        return new AlbumResults(0, 30, 30, albumList);
    }
}
