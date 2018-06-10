package com.masbon.worldcupeditor.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.core.ImageEditorView;
import com.masbon.worldcupeditor.presentation.presenters.fragment.TransformPresenter;
import com.masbon.worldcupeditor.presentation.views.fragment.TransformView;
import com.masbon.worldcupeditor.ui.activities.EditorActivity;
import com.masbon.worldcupeditor.utils.ToolbarUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.masbon.worldcupeditor.core.enums.EditorTool.TRANSFORM;
import static com.masbon.worldcupeditor.core.enums.EditorTool.TRANSFORM_HORIZONTAL;
import static com.masbon.worldcupeditor.core.enums.EditorTool.TRANSFORM_STRAIGHTEN;
import static com.masbon.worldcupeditor.core.enums.EditorTool.TRANSFORM_VERTICAL;

public class TransformFragment extends MvpAppCompatFragment implements TransformView {
    @InjectPresenter
    TransformPresenter mPresenter;

    private Unbinder mUnbinder;

    private EditorActivity mEditorActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mEditorActivity = (EditorActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transform, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ImageEditorView) getActivity().findViewById(R.id.image_editor_view))
                .changeTool(TRANSFORM);
        ToolbarUtil.updateTitle(R.string.transform, getActivity());
        ToolbarUtil.updateSubtitle(null, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        super.onPrepareOptionsMenu(menu);
    }

    @OnClick(R.id.transformHorizontalButton)
    void onClickTransformHorizontal() {
        mEditorActivity.setupFragment(
                ImageAdjustmentFragment.newInstance(TRANSFORM_HORIZONTAL)
        );
    }

    @OnClick(R.id.transformStraightenButton)
    void onClickTransformStraighten() {
        mEditorActivity.setupFragment(
                ImageAdjustmentFragment.newInstance(TRANSFORM_STRAIGHTEN)
        );
    }

    @OnClick(R.id.transformVerticalButton)
    void onClickTransformVertical() {
        mEditorActivity.setupFragment(
                ImageAdjustmentFragment.newInstance(TRANSFORM_VERTICAL)
        );
    }
}
