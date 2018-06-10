package com.masbon.worldcupeditor.presentation.presenters.fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.App;
import com.masbon.worldcupeditor.models.Font;
import com.masbon.worldcupeditor.presentation.views.fragment.FontsView;

import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class FontsPresenter extends MvpPresenter<FontsView> {
    @Inject
    List<Font> mFonts;

    public FontsPresenter() {
        App.getAppComponent().inject(this);

        getViewState().setupAdapter(mFonts);
    }

}
