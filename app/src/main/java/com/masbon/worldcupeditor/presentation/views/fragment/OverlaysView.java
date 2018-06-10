package com.masbon.worldcupeditor.presentation.views.fragment;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;

import com.masbon.worldcupeditor.models.Overlay;

import java.util.List;

public interface OverlaysView extends MvpView {
    void setupAdapter(List<Overlay> overlays);

    void onOverlayChanged(Bitmap bitmap);
}
