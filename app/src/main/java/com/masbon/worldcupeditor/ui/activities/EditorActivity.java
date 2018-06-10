package com.masbon.worldcupeditor.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.core.EditorListener;
import com.masbon.worldcupeditor.core.ImageEditorView;
import com.masbon.worldcupeditor.presentation.presenters.activity.EditorActivityPresenter;
import com.masbon.worldcupeditor.presentation.views.activity.EditorActivityView;
import com.masbon.worldcupeditor.tasks.ImageSaveTask;
import com.masbon.worldcupeditor.ui.dialogs.LoadingDialog;
import com.masbon.worldcupeditor.ui.fragments.TransparencyFragment;
import com.masbon.worldcupeditor.ui.fragments.ToolsFragment;
import com.masbon.worldcupeditor.utils.ToolbarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.masbon.worldcupeditor.presentation.presenters.activity.EditorActivityPresenter.FACEBOOK_PACKAGE_NAME;
import static com.masbon.worldcupeditor.presentation.presenters.activity.EditorActivityPresenter.INSTAGRAM_PACKAGE_NAME;

public class EditorActivity extends MvpAppCompatActivity implements EditorActivityView {
    @InjectPresenter
    EditorActivityPresenter mPresenter;

    @ProvidePresenter
    EditorActivityPresenter provideEditorPresenter() {
        return new EditorActivityPresenter(this, getIntent());
    }

    @BindView(R.id.toolbar_editor)
    Toolbar mToolbar;

    @BindView(R.id.button_undo)
    Button mUndoButton;

    @BindView(R.id.image_editor_view)
    ImageEditorView mImageEditorView;

    @BindView(R.id.frame_layout_fragment_container)
    FrameLayout mFragmentContainer;

    private MenuPopupHelper mMenuPopupHelper;

    private FragmentManager mFragmentManager;

    private LoadingDialog mLoadingDialog;

    private AdView mAdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        }

        mLoadingDialog = new LoadingDialog(this);

        mImageEditorView.setMvpDelegate(getMvpDelegate());

        mImageEditorView.setUndoListener(new EditorListener() {
            @Override
            public void onTransparencyHandleButtonClicked(Paint paint) {
                setupFragment(TransparencyFragment.newInstance(paint));
            }

            @Override
            public void hasChanges(int count) {
                if (count != 0) {
                    mUndoButton.setText(String.valueOf(count));
                } else {
                    mUndoButton.setText(String.valueOf(0));
                    mUndoButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAppliedImageSaved(Uri uri) {
                // set the image uri from cache for share this image later
                mPresenter.setAlteredImageUri(uri);

                // ToolsFragment.class.getSimpleName();

                // return to ToolsFragment
                mFragmentManager.popBackStack();
            }
        });

        ToolbarUtil.showTitle(false, this);

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(
                mFragmentContainer.getId(), new ToolsFragment(), ToolsFragment.class.getSimpleName()
        ).commit();


        Log.i("BackStack", String.valueOf(mFragmentManager.getBackStackEntryCount()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                showSharePopupMenu();
                break;
            case R.id.action_apply:
                mImageEditorView.applyChanges();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    private void showSharePopupMenu() {
        if (mMenuPopupHelper == null) {
            MenuBuilder menuBuilder = new MenuBuilder(this);
            menuBuilder.setCallback(new MenuBuilder.Callback() {
                @Override
                public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                    Bitmap bitmap = mImageEditorView.getAlteredImageBitmap();

                    switch (item.getItemId()) {
                        case R.id.action_save:
                            mPresenter.save(bitmap);
                            return true;
                        case R.id.action_instagram:
                            mPresenter.share(INSTAGRAM_PACKAGE_NAME);
                            return true;
                        case R.id.action_facebook:
                            mPresenter.share(FACEBOOK_PACKAGE_NAME);
                            return true;
                        case R.id.action_more:
                            mPresenter.share(null);
                            return true;
                    }
                    return false;
                }

                @Override
                public void onMenuModeChange(MenuBuilder menu) {

                }
            });

            MenuInflater menuInflater = new MenuInflater(this);
            menuInflater.inflate(R.menu.menu_share, menuBuilder);
            mMenuPopupHelper = new MenuPopupHelper(this, menuBuilder, findViewById(R.id.action_share));
            mMenuPopupHelper.setForceShowIcon(true);
            mMenuPopupHelper.show();
        } else {
            mMenuPopupHelper.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() == 0) {
            mPresenter.onBackPressed(mImageEditorView.getAlteredImageBitmap());
        } else if (mFragmentManager.getBackStackEntryCount() == 1) {
            mToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
            ToolbarUtil.showTitle(false, this);
            navigateBack(true);
        } else if (mFragmentManager.getBackStackEntryCount() > 1) {
            ToolbarUtil.updateSubtitle(null, this);
            navigateBack(true);
        }
    }

    @Override
    public void startEditing(Bitmap bitmap) {
        mImageEditorView.setImageBitmap(bitmap);
    }

    @Override
    public void showLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        builder.setMessage(getString(R.string.on_back_alert))
                .setPositiveButton(getString(R.string.ok), (dialogInterface, i) -> finish())
                .setNeutralButton(getString(R.string.save), ((dialogInterface1, i) ->
                        new ImageSaveTask(this, mImageEditorView.getAlteredImageBitmap()).execute())
                )
                .setNegativeButton(getString(R.string.cancel), (dialogInterface, i1) -> dialogInterface.dismiss())
                .show();
    }

    @Override
    public void showApplicationNotExistAlertDialog(@StringRes int messageBody, @NonNull String packageName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        builder.setTitle(getString(R.string.application_does_not_exist));
        builder.setMessage(getString(messageBody));
        builder.setPositiveButton(getString(R.string.install), (dialogInterface, i) -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + packageName)));
            } catch (android.content.ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        });

        builder.setNegativeButton(getString(R.string.dismiss), (dialogInterface, i1) -> dialogInterface.dismiss());
        builder.show();
    }

    public void setupFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(mFragmentContainer.getId(), fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        ToolbarUtil.showTitle(true, this);
        mUndoButton.setVisibility(View.GONE);

        Log.i("BackStack", String.valueOf(mFragmentManager.getBackStackEntryCount()));
    }

    @Override
    public void showToastMessage(int stringResource) {
        Toast.makeText(getApplicationContext(), getString(stringResource), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateBack(boolean isFragment) {
        if (isFragment) {
            if (mFragmentManager.getBackStackEntryCount() > 1)
                super.onBackPressed();
            else if (mFragmentManager.getBackStackEntryCount() == 0)
                mPresenter.onBackPressed(mImageEditorView.getAlteredImageBitmap());
            else if (mFragmentManager.getBackStackEntryCount() == 1) {
                super.onBackPressed();
            }
        } else finish();
    }

    @Override
    public void share(@NonNull Uri uri, @Nullable String packageName) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        if (packageName != null) {
            intent.setPackage(packageName);
        }

        startActivity(intent);
    }

    @OnClick(R.id.button_undo)
    void onClickUndo() {
        mImageEditorView.undo();
    }
}