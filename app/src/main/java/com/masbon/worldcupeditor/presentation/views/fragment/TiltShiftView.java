package com.masbon.worldcupeditor.presentation.views.fragment;

import com.arellomobile.mvp.MvpView;

import com.masbon.worldcupeditor.core.enums.EditorTool;

public interface TiltShiftView extends MvpView {
    void onTiltShiftChanged(EditorTool command);
}