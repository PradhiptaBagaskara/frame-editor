package com.masbon.worldcupeditor.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.masbon.worldcupeditor.App;
import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.adapters.FontsAdapter;
import com.masbon.worldcupeditor.models.Font;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.graphics.Color.TRANSPARENT;

public class FontPickerDialog extends Dialog {
    @Inject
    List<Font> mFonts;

    @BindView(R.id.fontsRecyclerView)
    RecyclerView fontsList;

    private String mText;
    private Context mContext;

    private Typeface mTypeface;

    private OnFontClickListener mOnFontClickListener;

    public interface OnFontClickListener {
        void onClick(Typeface typeface);
    }

    private boolean mBold;
    private boolean mItalic;

    public FontPickerDialog(Context context) {
        super(context);
        mContext = context;
    }

    public FontPickerDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
        }
        setContentView(R.layout.dialog_font_picker);

        ButterKnife.bind(this);

        App.getAppComponent().inject(this);

        initFontsList();
    }

    /*@OnClick(R.id.applyFontButton)
    void onClickApply() {
        mOnFontClickListener.onFilterClicked(mTypeface);
        dismiss();
    }

    @OnClick(R.id.cancelTextStyle)
    void onClickCancel() {
        dismiss();
    }*/

    private void initFontsList() {
        FontsAdapter fontsAdapter = new FontsAdapter(mFonts);
        fontsList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        fontsAdapter.setOnFontClickListener(font ->
                mTypeface = Typeface.createFromAsset(mContext.getAssets(), font.getPath())
        );

        fontsList.setAdapter(fontsAdapter);
    }

    public void setOnFontClickListener(OnFontClickListener onFontClickListener) {
        mOnFontClickListener = onFontClickListener;
    }

    public boolean isItalic() {
        return mItalic;
    }

    public void setItalic(boolean italic) {
        mItalic = italic;
    }

    public boolean isBold() {
        return mBold;
    }

    public void setBold(boolean bold) {
        mBold = bold;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }
}
