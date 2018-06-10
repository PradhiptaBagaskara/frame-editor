package com.masbon.worldcupeditor.presentation.views.activity;

import android.net.Uri;
import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;

public interface HomeView extends MvpView {
    void  prepareAd();

    void startGallery();

    void startCamera(Uri uri);

    void startEditing(String photoPath);

    void showPermissionDenied(@StringRes int message);
}
