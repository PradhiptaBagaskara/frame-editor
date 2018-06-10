package com.masbon.worldcupeditor.presentation.presenters.fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.core.enums.EditorTool;
import com.masbon.worldcupeditor.presentation.views.fragment.TransformView;

@InjectViewState
public class TransformPresenter extends MvpPresenter<TransformView> {

    public void setupTransform(EditorTool editorTool) {
        // TODO: mEditorView.setupToolFragment(editorTool);
    }
}