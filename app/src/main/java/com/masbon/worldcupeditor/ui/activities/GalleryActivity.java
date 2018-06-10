package com.masbon.worldcupeditor.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.models.ImageAlbum;
import com.masbon.worldcupeditor.presentation.presenters.activity.GalleryActivityPresenter;
import com.masbon.worldcupeditor.presentation.views.activity.GalleryView;
import com.masbon.worldcupeditor.ui.fragments.GalleryAlbumsFragment;
import com.masbon.worldcupeditor.ui.fragments.GalleryImagesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends MvpAppCompatActivity implements GalleryView {
    @InjectPresenter
    GalleryActivityPresenter mPresenter;

    @BindView(R.id.toolbar_gallery)
    Toolbar mToolbar;

    private FragmentManager mFragmentManager;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        }

        mFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.galleryFragmentFrameLayout, new GalleryAlbumsFragment())
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() == 1) {
            super.onBackPressed();
            mToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
            mToolbar.setTitle(R.string.gallery);
        } else {
            finish();
        }
    }

    @Override
    public void showImages(ImageAlbum imageAlbum) {
        mToolbar.setTitle(imageAlbum.getName());
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.galleryFragmentFrameLayout, GalleryImagesFragment.newInstance(imageAlbum.getImages()))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }
}