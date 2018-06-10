package com.masbon.worldcupeditor.presentation.presenters.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.App;
import com.masbon.worldcupeditor.models.BrushSize;
import com.masbon.worldcupeditor.models.EditorColor;
import com.masbon.worldcupeditor.presentation.views.fragment.DrawingView;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

@InjectViewState
public class DrawingPresenter extends MvpPresenter<DrawingView> {
    @Inject
    Lazy<List<BrushSize>> mSizes;

    @Inject
    Lazy<List<EditorColor>> mColors;

    public DrawingPresenter() {
        App.getAppComponent().inject(this);

        getViewState().setupSizesAdapter(mSizes.get());
        getViewState().setupColorsAdapter(mColors.get());
    }

    public void changeBrushColor(@NonNull Context context, EditorColor editorColor) {
        int color = ResourcesCompat.getColor(context.getResources(), editorColor.getColor(), null);

        getViewState().onBrushColorChanged(color);
    }
}