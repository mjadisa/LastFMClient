package com.example.lastfmclient.net;

import com.example.lastfmclient.data.albumDetails.AlbumDetails;
import com.example.lastfmclient.data.albumResults.AlbumResultsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFMService {

    @GET(".")
    Single<AlbumResultsResponse> getAlbumSearchResults(@Query("method") String methodName,
                                                       @Query("album") String albumName,
                                                       @Query("limit") int limit,
                                                       @Query("page") int page,
                                                       @Query("format") String responseFormat);


    @GET(".")
    Single<AlbumDetails> getAlbumInfo(@Query("method") String methodName,
                                      @Query("album") String albumName,
                                      @Query("artist") String albumArtist,
                                      @Query("format") String responseFormat);
}
