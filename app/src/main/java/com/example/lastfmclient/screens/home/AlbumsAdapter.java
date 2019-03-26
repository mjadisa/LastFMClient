package com.example.lastfmclient.screens.home;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lastfmclient.R;
import com.example.lastfmclient.data.albumResults.Album;
import com.example.lastfmclient.data.albumResults.Image;
import com.example.lastfmclient.databinding.ItemAlbumBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder> {

    private static OnAlbumSelectedListener listener = null;
    private final List<Album> albums;

    public AlbumsAdapter(OnAlbumSelectedListener listener) {
        this.albums = new ArrayList<>();
        AlbumsAdapter.listener = listener;
    }

    public void setData(List<Album> albums) {
        this.albums.clear();
        this.albums.addAll(albums);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new AlbumViewHolder(ItemAlbumBinding.inflate(layoutInflater, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder albumViewHolder, int position) {
        albumViewHolder.bind(albums.get(position));
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        private final ItemAlbumBinding itemAlbumBinding;

        public AlbumViewHolder(@NonNull ItemAlbumBinding itemAlbumBinding) {
            super(itemAlbumBinding.getRoot());
            this.itemAlbumBinding = itemAlbumBinding;
        }

        @BindingAdapter("imageUrl")
        public static void getAlbumArt(ImageView imageView, List<Image> imageList) {
            Image largeImage = imageList.get(imageList.size() - 1);
            if (largeImage != null && !largeImage.getText().isEmpty()) {
                Picasso.get()
                        .load(largeImage.getText())
                        .error(R.mipmap.ic_launcher_round)
                        .into(imageView);
            } else {
                Picasso.get().load(R.mipmap.ic_launcher_round).into(imageView);
            }
        }

        void bind(Album album) {
            itemAlbumBinding.setAlbum(album);
            if (listener != null) {
                itemAlbumBinding.setListener(listener);
            }
            itemAlbumBinding.executePendingBindings();
        }
    }

}
