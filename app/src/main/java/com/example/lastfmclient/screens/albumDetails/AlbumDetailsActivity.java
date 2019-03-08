package com.example.lastfmclient.screens.albumDetails;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.lastfmclient.R;
import com.example.lastfmclient.common.Constants;
import com.example.lastfmclient.databinding.ActivityAlbumDetailsBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class AlbumDetailsActivity extends AppCompatActivity {

    @Inject
    AlbumDetailsViewModel albumDetailsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        ActivityAlbumDetailsBinding albumDetailsBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_album_details);

        albumDetailsViewModel.getAlbumDetailsObservable().observe(this,
                albumDetailsBinding::setAlbumDetails);

        albumDetailsViewModel.getAlbumDetails(getIntent().getStringExtra(Constants.KEY_ALBUM_NAME),
                getIntent().getStringExtra(Constants.KEY_ALBUM_ARTIST));

    }
}
