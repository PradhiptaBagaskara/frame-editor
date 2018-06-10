package com.masbon.worldcupeditor.presentation.views.fragment;

import com.arellomobile.mvp.MvpView;

import com.masbon.worldcupeditor.models.EditorColor;

import java.util.List;

public interface ColorsView extends MvpView {
    void setupAdapter(List<EditorColor> colors);
}
