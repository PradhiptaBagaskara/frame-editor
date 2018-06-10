package com.masbon.worldcupeditor.ui.fragments;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.adapters.StickerSetAdapter;
import com.masbon.worldcupeditor.core.ImageEditorView;
import com.masbon.worldcupeditor.models.Sticker;
import com.masbon.worldcupeditor.models.StickersSet;
import com.masbon.worldcupeditor.presentation.presenters.fragment.StickersSetPresenter;
import com.masbon.worldcupeditor.presentation.views.fragment.StickersSetView;
import com.masbon.worldcupeditor.ui.activities.EditorActivity;
import com.masbon.worldcupeditor.utils.ToolbarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.masbon.worldcupeditor.core.enums.EditorTool.STICKERS;

public class StickersSetFragment extends ToolFragment implements StickersSetView {
    @InjectPresenter
    StickersSetPresenter mPresenter;

    @BindView(R.id.stickersSetRecyclerView)
    RecyclerView mRecyclerView;

    private Unbinder mUnbinder;

    private ImageEditorView mImageEditorView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageEditorView = (ImageEditorView) getActivity().findViewById(R.id.image_editor_view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stickers, container, false);

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
        mImageEditorView.changeTool(STICKERS);
        ToolbarUtil.updateTitle(R.string.stickers, getActivity());
        ToolbarUtil.updateSubtitle(null, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void setupAdapter(List<StickersSet> stickersSets) {
        StickerSetAdapter adapter = new StickerSetAdapter(stickersSets);
        adapter.setStickerSetClickListener(stickersSet ->
                mPresenter.stickersSetClicked(stickersSet)
        );

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showStickers(@StringRes int title, ArrayList<Sticker> stickers) {
        ((EditorActivity) getActivity()).setupFragment(StickersFragment.newInstance(title, stickers));
    }
}