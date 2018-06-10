package com.masbon.worldcupeditor.presentation.views.fragment;

import com.arellomobile.mvp.MvpView;

import com.masbon.worldcupeditor.models.Tool;

import java.util.List;

public interface ToolsView extends MvpView {
    void setupTools(List<Tool> tools);
}
