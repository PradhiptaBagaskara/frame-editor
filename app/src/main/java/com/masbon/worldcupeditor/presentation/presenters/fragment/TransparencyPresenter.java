package com.masbon.worldcupeditor.presentation.presenters.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.models.ParcelablePaint;
import com.masbon.worldcupeditor.presentation.views.fragment.IntensityView;
import com.masbon.worldcupeditor.ui.fragments.TransparencyFragment;

@InjectViewState
public class TransparencyPresenter extends MvpPresenter<IntensityView> {
    private final int mOldValue;

    private Paint mPaint;

    public TransparencyPresenter(@NonNull Bundle bundle) {
        ParcelablePaint parcelablePaint = bundle.getParcelable(TransparencyFragment.ARG_PARAM);
        if (parcelablePaint != null) {
            mPaint = parcelablePaint.getPaint();
        }

        mOldValue = mPaint.getAlpha();
    }

    public void progressChanged(int value) {
        int alpha = (int) Math.round((2.55 * value));

        Log.i("TransparencyChanged", String.valueOf(alpha));

        mPaint.setAlpha(alpha);
        getViewState().onTransparencyChanged(getStringValue(value));
    }

    public void cancelChanges() {
        mPaint.setAlpha(mOldValue);
        getViewState().onTransparencyChanged(getStringValue(mOldValue));
    }

    public void applyChanges() {

    }

    private String getStringValue(int value) {
        return String.valueOf(value) + "%";
    }
}