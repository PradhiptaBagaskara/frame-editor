package com.masbon.worldcupeditor.presentation.views.fragment;

import android.support.annotation.ColorInt;

import com.arellomobile.mvp.MvpView;

import com.masbon.worldcupeditor.models.BrushSize;
import com.masbon.worldcupeditor.models.EditorColor;

import java.util.List;

public interface DrawingView extends MvpView {
    void setupSizesAdapter(List<BrushSize> sizes);

    void setupColorsAdapter(List<EditorColor> colors);

    void onBrushColorChanged(@ColorInt int color);
}
