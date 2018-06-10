package com.masbon.worldcupeditor.presentation.presenters.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.App;
import com.masbon.worldcupeditor.models.Frame;
import com.masbon.worldcupeditor.presentation.views.fragment.FramesView;
import com.masbon.worldcupeditor.utils.BitmapUtil;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

@InjectViewState
public class FramesPresenter extends MvpPresenter<FramesView> {
    @Inject
    Lazy<List<Frame>> mFrames;

    public FramesPresenter() {
        App.getAppComponent().inject(this);
        getViewState().setupAdapter(mFrames.get());
    }

    public void changeOverlay(@NonNull Context context, Frame frame) {
        Bitmap bitmap = BitmapUtil.drawable2Bitmap(context, frame.getImage());

        getViewState().onFrameChanged(bitmap);
    }
}