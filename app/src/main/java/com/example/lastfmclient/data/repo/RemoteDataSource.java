package com.example.lastfmclient.data.repo;

import com.example.lastfmclient.common.Constants;
import com.example.lastfmclient.data.model.AlbumResults;
import com.example.lastfmclient.net.LastFMService;

import io.reactivex.Maybe;

public class RemoteDataSource implements DataSource {

    private final LastFMService lastFMService;

    public RemoteDataSource(LastFMService lastFMService) {
        this.lastFMService = lastFMService;
    }


    @Override
    public Maybe<AlbumResults> getAlbums(String albumName, int page) {
        return lastFMService.getAlbumSearchResults(Constants.QUERY_ALBUM_SEARCH,
                albumName,
                Constants.RESULTS_PER_PAGE,
                page,
                Constants.RESULT_FORMAT)
                .flatMapMaybe(result -> Maybe.just(new AlbumResults(
                        Integer.parseInt(result.getResults().getOpensearchStartIndex()),
                        Integer.parseInt(result.getResults().getOpensearchItemsPerPage()),
                        Integer.parseInt(result.getResults().getOpensearchTotalResults()),
                        result.getResults().getAlbummatches().getAlbum())));
    }
}
