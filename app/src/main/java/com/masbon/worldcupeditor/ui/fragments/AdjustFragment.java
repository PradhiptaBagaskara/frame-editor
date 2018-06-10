package com.masbon.worldcupeditor.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import com.masbon.worldcupeditor.adapters.AdjustAdapter;
import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.core.ImageEditorView;
import com.masbon.worldcupeditor.models.Adjust;
import com.masbon.worldcupeditor.presentation.presenters.fragment.AdjustPresenter;
import com.masbon.worldcupeditor.presentation.views.fragment.AdjustView;
import com.masbon.worldcupeditor.ui.activities.EditorActivity;
import com.masbon.worldcupeditor.utils.ToolbarUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.masbon.worldcupeditor.core.enums.EditorTool.NONE;

public class AdjustFragment extends MvpAppCompatFragment implements AdjustView {
    @InjectPresenter
    AdjustPresenter mPresenter;

    @BindView(R.id.recycler_view_adjust)
    RecyclerView mRecyclerView;

    private Unbinder mUnbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adjust, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ImageEditorView) getActivity().findViewById(R.id.image_editor_view))
                .changeTool(NONE);
        ToolbarUtil.updateTitle(R.string.adjust, getActivity());
        ToolbarUtil.updateSubtitle(null, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.removeItem(R.id.action_share);
    }

    @Override
    public void setupAdapter(List<Adjust> adjusts) {
        AdjustAdapter adapter = new AdjustAdapter(adjusts);
        adapter.setOnAdjustClickListener(adjust -> mPresenter.changeAdjust(adjust));
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(null, LinearLayout.HORIZONTAL, false)
        );
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void adjustChanged(Fragment fragment) {
        ((EditorActivity) getActivity())
                .setupFragment(fragment);
    }
}