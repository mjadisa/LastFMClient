package com.example.lastfmclient;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import com.example.lastfmclient.data.albumResults.Album;
import com.example.lastfmclient.data.model.AlbumResults;
import com.example.lastfmclient.data.repo.LastFMRepository;
import com.example.lastfmclient.screens.home.HomeViewModel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private LastFMRepository lastFMRepository;

    @Mock
    private Observer<List<Album>> albumsObserver;

    private final AlbumResults albumResults = TestUtils.generateAlbumSearchResults();

    private HomeViewModel homeViewModel;

    @BeforeClass
    public static void setupRxSchedulers() {
        Scheduler scheduler = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run, false);
            }
        };
        RxJavaPlugins.setInitIoSchedulerHandler(schedulerCallable -> scheduler);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> scheduler);
    }

    @Before
    public void setup() {
        homeViewModel = new HomeViewModel(lastFMRepository);
    }

    @Test
    public void testSuccess() {
        //Given
        when(lastFMRepository.getAlbums(anyString(), anyInt()))
                .thenReturn(Maybe.just(albumResults));
        //When
        homeViewModel.getAlbumsObservable().observeForever(albumsObserver);
        homeViewModel.getAlbums("Mockito");

        //Then
        verify(albumsObserver).onChanged(albumResults.getAlbumList());
    }


}
