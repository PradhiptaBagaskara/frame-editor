package com.masbon.worldcupeditor.ui.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.core.ImageEditorView;
import com.masbon.worldcupeditor.models.Text;
import com.masbon.worldcupeditor.presentation.presenters.fragment.AddTextPresenter;
import com.masbon.worldcupeditor.presentation.views.fragment.AddTextView;
import com.masbon.worldcupeditor.ui.dialogs.FontPickerDialog;
import com.masbon.worldcupeditor.utils.ToolbarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.masbon.worldcupeditor.core.enums.EditorTool.TEXT;

public class TextFragment extends ToolFragment implements AddTextView {
    @InjectPresenter
    AddTextPresenter mPresenter;

    @BindView(R.id.edit_text)
    EditText mEditText;

    private Context mContext;

    private int mColor = Color.BLACK;

    private Typeface mTypeface = Typeface.DEFAULT;

    private Unbinder mUnbinder;

    private FontPickerDialog mFontPickerDialog;
    private ImageEditorView mImageEditorView;

    public static TextFragment newInstance() {
        return new TextFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageEditorView = (ImageEditorView) getActivity().findViewById(R.id.image_editor_view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        mContext = view.getContext();
        mFontPickerDialog = new FontPickerDialog(mContext);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mImageEditorView.changeTool(TEXT);
        ToolbarUtil.updateTitle(R.string.text, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void addText(Text text) {
        mImageEditorView.addText(text);
    }

    @Override
    public void onTextColorChanged(@ColorInt int color) {

    }

    @Override
    public void showToastMessage(@StringRes int messageText) {
        Toast.makeText(mContext, getResources().getString(messageText), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.addTextButton)
    void onClickAddText() {
        mPresenter.addText(mEditText.getText().toString(), mTypeface, mColor);
    }

    @OnClick(R.id.selectTextColorButton)
    void onClickTextColorButton() {
        //((EditorActivity) getActivity()).setupFragment(ColorsFragment.newInstance());
    }

    @OnClick(R.id.selectFontButton)
    void onClickTextButton() {
        mFontPickerDialog.show();
    }
}