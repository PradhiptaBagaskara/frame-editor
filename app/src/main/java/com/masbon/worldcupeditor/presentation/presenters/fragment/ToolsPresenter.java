package com.masbon.worldcupeditor.presentation.presenters.fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.App;
import com.masbon.worldcupeditor.models.Tool;
import com.masbon.worldcupeditor.presentation.views.fragment.ToolsView;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

@InjectViewState
public class ToolsPresenter extends MvpPresenter<ToolsView> {
    @Inject
    Lazy<List<Tool>> mTools;

    public ToolsPresenter() {
        App.getAppComponent().inject(this);
        getViewState().setupTools(mTools.get());
    }
}