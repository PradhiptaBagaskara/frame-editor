package com.masbon.worldcupeditor.presentation.presenters.fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.App;
import com.masbon.worldcupeditor.models.EditorColor;
import com.masbon.worldcupeditor.presentation.views.fragment.ColorsView;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class ColorsPresenter extends MvpPresenter<ColorsView> {
    @Inject
    List<EditorColor> mColors;

    public ColorsPresenter() {
        App.getAppComponent().inject(this);

        getViewState().setupAdapter(mColors);
    }

}