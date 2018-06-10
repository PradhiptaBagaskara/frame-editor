package com.masbon.worldcupeditor.presentation.views.fragment;

import com.arellomobile.mvp.MvpView;

import com.masbon.worldcupeditor.models.ImageAlbum;

import java.util.List;

public interface GalleryAlbumsView extends MvpView {
    void showNoImages();

    void setupAdapter(List<ImageAlbum> imageAlbums);
}
