package com.masbon.worldcupeditor.presentation.presenters.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.App;
import com.masbon.worldcupeditor.models.Overlay;
import com.masbon.worldcupeditor.presentation.views.fragment.OverlaysView;
import com.masbon.worldcupeditor.utils.BitmapUtil;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

@InjectViewState
public class OverlaysPresenter extends MvpPresenter<OverlaysView> {
    @Inject
    Lazy<List<Overlay>> mOverlays;

    public OverlaysPresenter() {
        App.getAppComponent().inject(this);
        getViewState().setupAdapter(mOverlays.get());
    }

    public void changeOverlay(@NonNull Context context, Overlay overlay) {
        Bitmap bitmap = BitmapUtil.drawable2Bitmap(context, overlay.getImage());

        getViewState().onOverlayChanged(bitmap);
    }
}