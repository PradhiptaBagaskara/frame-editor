package com.masbon.worldcupeditor.presentation.presenters.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.models.EditorColor;
import com.masbon.worldcupeditor.models.Text;
import com.masbon.worldcupeditor.presentation.views.fragment.AddTextView;

@InjectViewState
public class AddTextPresenter extends MvpPresenter<AddTextView> {
    public void changeTextColor(@NonNull Context context, EditorColor editorColor) {
        int color = ResourcesCompat.getColor(context.getResources(), editorColor.getColor(), null);
    }

    public void addText(String text, Typeface typeface, int color) {
        if (!text.isEmpty()) {
            Text texts = new Text(text, typeface, color);
            getViewState().addText(texts);
        } else
            getViewState().showToastMessage(R.string.text_is_empty);
    }
}