package com.example.lastfmclient.common;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class PaginationListener extends RecyclerView.OnScrollListener {
    private final LinearLayoutManager linearLayoutManager;
    private final PaginationStateListener listener;

    public PaginationListener(@NonNull LinearLayoutManager linearLayoutManager,
                              @NonNull PaginationStateListener paginationStateListener) {
        this.linearLayoutManager = linearLayoutManager;
        this.listener = paginationStateListener;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = linearLayoutManager.getChildCount();
        int totalCount = linearLayoutManager.getItemCount();
        int firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
        if (!listener.isLastPage() && !listener.isLoading()) {
            if ((visibleItemCount + firstVisiblePosition) >= totalCount &&
                    firstVisiblePosition >= 0 &&
                    totalCount >= Constants.RESULTS_PER_PAGE) {
                listener.loadMoreItems();
            }
        }
    }

    public interface PaginationStateListener {
        boolean isLoading();

        boolean isLastPage();

        void loadMoreItems();
    }
}
