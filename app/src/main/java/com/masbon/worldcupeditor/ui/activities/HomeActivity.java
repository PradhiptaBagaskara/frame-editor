package com.masbon.worldcupeditor.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.presentation.presenters.activity.HomeActivityPresenter;
import com.masbon.worldcupeditor.presentation.views.activity.HomeView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.ads.MobileAds;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    private static final String TAG = "HomeActivity";

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    // ...

    public static final int REQ_CAMERA = 1;

    @InjectPresenter
    HomeActivityPresenter mPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        setContentView(R.layout.activity_home);

        prepareAd();

        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                Log.i("hello", "world");
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("TAG"," Interstitial not loaded");
                        }

                        prepareAd();
                    }
                });
            }
        }, 60, 60, TimeUnit.SECONDS);





        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ButterKnife.bind(this);
    }

    @Override
    public void  prepareAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ads2));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    @OnClick(R.id.button_camera)
    void onClickCamera() {
        mPresenter.openCamera(this);
    }

    @OnClick(R.id.gallery_button)
    void onClickGallery() {
        mPresenter.openGallery(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.startEditing(requestCode, resultCode);
    }

    @Override
    public void startGallery() {
        Intent intent = new Intent(HomeActivity.this, GalleryActivity.class);
        startActivity(intent);
    }

    @Override
    public void startCamera(Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, REQ_CAMERA);
        }
    }

    @Override
    public void startEditing(String photoPath) {
        Intent intent = new Intent(HomeActivity.this, PreviewActivity.class);
        intent.putExtra(PreviewActivity.IMAGE_PATH, photoPath);
        startActivity(intent);
    }

    @Override
    public void showPermissionDenied(@StringRes int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog)
                .setTitle(getString(R.string.permission_denied))
                .setMessage(getString(message))
                .setPositiveButton(getString(R.string.go_to_app_settings), (dialogInterface, i) -> {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + "com.masbon.worldcupeditor"));
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton(getString(R.string.dismiss), (dialogInterface, i1) -> dialogInterface.dismiss());
        builder.show();
    }
}