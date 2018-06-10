package com.masbon.worldcupeditor.presentation.presenters.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.core.enums.EditorTool;
import com.masbon.worldcupeditor.presentation.views.fragment.ImageAdjustmentView;
import com.masbon.worldcupeditor.ui.fragments.ImageAdjustmentFragment;

import static com.masbon.worldcupeditor.core.enums.EditorTool.NONE;

@InjectViewState
public class ImageAdjustmentPresenter extends MvpPresenter<ImageAdjustmentView> {
    private EditorTool mCurrentCommand = NONE;

    public ImageAdjustmentPresenter(@NonNull Bundle bundle) {
        mCurrentCommand =
                (EditorTool) bundle.getSerializable(ImageAdjustmentFragment.ARG_PARAM);

        assert mCurrentCommand != null;
        Log.i("ImageAdjustment", "Current tool = " + mCurrentCommand.name());

        setupSlider();
    }

    public void onResume() {
        switch (mCurrentCommand) {
            case FILTERS:
                getViewState().changeToolbarSubtitle(R.string.intensity);
                break;
            case BRIGHTNESS:
                getViewState().changeToolbarSubtitle(R.string.brightness);
                break;
            case CONTRAST:
                getViewState().changeToolbarSubtitle(R.string.contrast);
                break;
            case SATURATION:
                getViewState().changeToolbarSubtitle(R.string.saturation);
                break;
            case WARMTH:
                getViewState().changeToolbarSubtitle(R.string.warmth);
                break;
            case TRANSFORM_STRAIGHTEN:
                getViewState().changeToolbarSubtitle(R.string.transform_straighten);
                break;
        }

        getViewState().changeToolbarTitle(getToolTitle());
        getViewState().setEditorTool(mCurrentCommand);
    }

    public void progressChanged(int value) {
        switch (mCurrentCommand) {
            case BRIGHTNESS:
                getViewState().onBrightnessChanged(value);
                break;
            case CONTRAST:
                getViewState().onContrastChanged(value);
                break;
            case SATURATION:
                getViewState().onSaturationChanged(value);
                break;
            case WARMTH:
                getViewState().onWarmthChanged(value);
                break;
            case TRANSFORM_STRAIGHTEN:
                getViewState().onStraightenTransformChanged(value);
                break;
            case VIGNETTE:
                getViewState().onVignetteChanged(value);
                break;
        }
    }

    private void setupSlider() {
        switch (mCurrentCommand) {
            case VIGNETTE:
                getViewState().setSeekBarValues(-100, 100, 70);
                break;
            case TRANSFORM_HORIZONTAL:
                getViewState().setSeekBarValues(-30, 30, 0);
                break;
            case TRANSFORM_STRAIGHTEN:
                getViewState().setSeekBarValues(-30, 30, 0);
                break;
            case TRANSFORM_VERTICAL:
                getViewState().setSeekBarValues(-30, 30, 0);
                break;
            default:
                getViewState().setSeekBarValues(-100, 100, 0);
                break;
        }
    }

    private int getToolTitle() {
        int title = R.string.adjust;

        switch (mCurrentCommand) {
            case FILTERS:
                title = R.string.filters;
                break;
            case TRANSFORM_STRAIGHTEN:
                title = R.string.transform;
                break;
            case VIGNETTE:
                title = R.string.vignette;
                break;
        }

        return title;
    }
}