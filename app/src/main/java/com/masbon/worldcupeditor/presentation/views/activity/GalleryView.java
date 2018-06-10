package com.masbon.worldcupeditor.presentation.views.activity;

import com.arellomobile.mvp.MvpView;

import com.masbon.worldcupeditor.models.ImageAlbum;

public interface GalleryView extends MvpView {
    void showImages(ImageAlbum imageAlbum);
}
