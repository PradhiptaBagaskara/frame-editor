package com.masbon.worldcupeditor.presentation.views.fragment;

import com.arellomobile.mvp.MvpView;

import com.masbon.worldcupeditor.models.Image;

import java.util.List;

public interface GalleryImagesView extends MvpView {
    void setupAdapter(List<Image> images);

    void editImage(String imagePath);
}
