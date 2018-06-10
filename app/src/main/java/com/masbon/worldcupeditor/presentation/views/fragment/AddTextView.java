package com.masbon.worldcupeditor.presentation.views.fragment;

import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;

import com.masbon.worldcupeditor.models.Text;

public interface AddTextView extends MvpView {
    void addText(Text text);

    void onTextColorChanged(@ColorInt int color);

    void showToastMessage(@StringRes int messageText);
}
