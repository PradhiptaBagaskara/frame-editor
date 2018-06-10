package com.masbon.worldcupeditor.presentation.views.fragment;

import com.arellomobile.mvp.MvpView;

import com.masbon.worldcupeditor.models.Font;

import java.util.List;

public interface FontsView extends MvpView {
    void setupAdapter(List<Font> fonts);
}
