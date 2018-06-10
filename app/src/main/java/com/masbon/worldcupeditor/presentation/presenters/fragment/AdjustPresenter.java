package com.masbon.worldcupeditor.presentation.presenters.fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.App;
import com.masbon.worldcupeditor.models.Adjust;
import com.masbon.worldcupeditor.presentation.views.fragment.AdjustView;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

@InjectViewState
public class AdjustPresenter extends MvpPresenter<AdjustView> {
    @Inject
    Lazy<List<Adjust>> mAdjusts;

    public AdjustPresenter() {
        App.getAppComponent().inject(this);
        getViewState().setupAdapter(mAdjusts.get());
    }

    public void changeAdjust(Adjust adjust) {
        getViewState().adjustChanged(adjust.getFragment());
    }
}