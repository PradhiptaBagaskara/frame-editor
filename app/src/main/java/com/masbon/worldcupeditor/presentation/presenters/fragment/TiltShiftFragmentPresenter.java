package com.masbon.worldcupeditor.presentation.presenters.fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.core.enums.EditorTool;
import com.masbon.worldcupeditor.presentation.views.fragment.TiltShiftView;

@InjectViewState
public class TiltShiftFragmentPresenter extends MvpPresenter<TiltShiftView> {
    public void changeTiltShift(EditorTool command) {
        getViewState().onTiltShiftChanged(command);
    }
}