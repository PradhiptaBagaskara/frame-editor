package com.masbon.worldcupeditor.presentation.views.fragment;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import com.masbon.worldcupeditor.models.Adjust;

import java.util.List;

public interface AdjustView extends MvpView {
    void setupAdapter(List<Adjust> adjusts);

    @StateStrategyType(SkipStrategy.class)
    void adjustChanged(Fragment fragment);
}
