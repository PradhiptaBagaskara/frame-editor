package com.masbon.worldcupeditor.core;

import android.graphics.Paint;
import android.net.Uri;

public interface EditorListener {
    void onTransparencyHandleButtonClicked(Paint paint);

    void hasChanges(int count);

    void onAppliedImageSaved(Uri uri);
}