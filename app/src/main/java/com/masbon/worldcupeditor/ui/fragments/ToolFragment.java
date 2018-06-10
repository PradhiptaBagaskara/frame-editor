package com.masbon.worldcupeditor.ui.fragments;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.arellomobile.mvp.MvpAppCompatFragment;

import com.masbon.worldcupeditor.R;

public abstract class ToolFragment extends MvpAppCompatFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.removeItem(R.id.action_share);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_tool, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}