package com.masbon.worldcupeditor.ui.fragments;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.adapters.ColorAdapter;
import com.masbon.worldcupeditor.adapters.SizesAdapter;
import com.masbon.worldcupeditor.core.ImageEditorView;
import com.masbon.worldcupeditor.models.BrushSize;
import com.masbon.worldcupeditor.models.EditorColor;
import com.masbon.worldcupeditor.presentation.presenters.fragment.DrawingPresenter;
import com.masbon.worldcupeditor.presentation.views.fragment.DrawingView;
import com.masbon.worldcupeditor.utils.ToolbarUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.masbon.worldcupeditor.core.enums.EditorTool.DRAWING;

public class DrawingFragment extends ToolFragment implements DrawingView {
    @InjectPresenter
    DrawingPresenter mPresenter;

    @BindView(R.id.recycler_view_sizes)
    RecyclerView mSizesRecyclerView;

    @BindView(R.id.recycler_view_colors)
    RecyclerView mColorsRecyclerView;

    private Unbinder mUnbinder;

    private ImageEditorView mImageEditorView;

    public static DrawingFragment newInstance() {
        return new DrawingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageEditorView = (ImageEditorView) getActivity().findViewById(R.id.image_editor_view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawing, container, false);

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
        mImageEditorView.changeTool(DRAWING);
        ToolbarUtil.updateTitle(R.string.drawing, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void setupSizesAdapter(List<BrushSize> sizes) {
        SizesAdapter adapter = new SizesAdapter(sizes);
        adapter.setOnSizeClickListener(size ->
                mImageEditorView.setBrushSize(size.getSize())
        );
        mSizesRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        mSizesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setupColorsAdapter(List<EditorColor> colors) {
        ColorAdapter adapter = new ColorAdapter(colors);
        adapter.setOnColorClickListener(editorColor ->
                mPresenter.changeBrushColor(getContext(), editorColor)
        );
        mColorsRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        mColorsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBrushColorChanged(@ColorInt int color) {
        mImageEditorView.setBrushColor(color);
    }
}