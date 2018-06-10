package com.masbon.worldcupeditor.presentation.views.fragment;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import com.masbon.worldcupeditor.models.Sticker;
import com.masbon.worldcupeditor.models.StickersSet;

import java.util.ArrayList;
import java.util.List;

public interface StickersSetView extends MvpView {
    void setupAdapter(List<StickersSet> stickersSets);

    @StateStrategyType(SkipStrategy.class)
    void showStickers(@StringRes int title, ArrayList<Sticker> stickers);
}