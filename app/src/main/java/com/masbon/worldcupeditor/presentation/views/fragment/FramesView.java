package com.masbon.worldcupeditor.presentation.views.fragment;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;

import com.masbon.worldcupeditor.models.Frame;

import java.util.List;

public interface FramesView extends MvpView {
    void setupAdapter(List<Frame> frames);

    void onFrameChanged(Bitmap bitmap);
}
