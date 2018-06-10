package com.masbon.worldcupeditor.presentation.presenters.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import com.masbon.worldcupeditor.App;
import com.masbon.worldcupeditor.models.Sticker;
import com.masbon.worldcupeditor.models.StickersSet;
import com.masbon.worldcupeditor.presentation.views.fragment.StickersView;
import com.masbon.worldcupeditor.ui.fragments.StickersFragment;
import com.masbon.worldcupeditor.ui.fragments.StickersSetFragment;
import com.masbon.worldcupeditor.utils.BitmapUtil;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

/**
 * Listens to user actions from the UI ({@link StickersFragment}), retrieves the data and updates
 * the UI as required.
 */
@InjectViewState
public class StickersPresenter extends MvpPresenter<StickersView> {
    @Inject
    Lazy<List<StickersSet>> mStickersSets;

    /**
     * Creates a presenter for the add/edit view.
     *
     * @param bundle contains stickers from set how was clicked on({@link StickersSetFragment}).
     */
    public StickersPresenter(@NonNull Bundle bundle) {
        App.getAppComponent().inject(this);
        setupStickersSet(bundle);
    }

    private void setupStickersSet(Bundle bundle) {
        List<Sticker> stickers = bundle.getParcelableArrayList(StickersFragment.ARG_STICKERS);

        getViewState().setupAdapter(stickers);
        getViewState().setupToolbarSubtitle(bundle.getInt(StickersFragment.ARG_TITLE));
    }

    /**
     * Get clicked sticker from res and add it on image.
     *
     * @param context to get bitmap from res.
     * @param sticker is clicked sticker to add on image.
     */
    public void stickerClicked(@NonNull Context context, Sticker sticker) {
        Bitmap bitmap = BitmapUtil.drawable2Bitmap(context, sticker.getImage());

        getViewState().addSticker(bitmap);
    }
}