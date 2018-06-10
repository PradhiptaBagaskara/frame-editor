package com.masbon.worldcupeditor.core;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import com.arellomobile.mvp.MvpView;

import com.masbon.worldcupeditor.core.enums.EditorTool;
import com.masbon.worldcupeditor.core.model.Drawing;
import com.masbon.worldcupeditor.core.model.EditorLinearTiltShift;
import com.masbon.worldcupeditor.core.model.EditorSticker;
import com.masbon.worldcupeditor.core.model.EditorText;
import com.masbon.worldcupeditor.core.model.EditorRadialTiltShift;
import com.masbon.worldcupeditor.core.model.EditorVignette;

import java.util.List;

interface EditorView extends MvpView {
    void setupImage(Bitmap bitmap, Matrix imageMatrix, RectF imageRect);

    void showOriginalImage(boolean display);

    void onToolChanged(EditorTool tool);

    void onImageAdjusted(Paint paint);

    void onOverlayChanged(Bitmap bitmap, Matrix matrix, Paint paint);

    void onFilterChanged(Paint paint);

    void onFrameChanged(Bitmap bitmap, Matrix matrix);

    void onTextAdded(List<EditorText> texts);

    void onStickerAdded(List<EditorSticker> stickers);

    void onVignetteUpdated(EditorVignette vignette);

    void onRadialTiltShiftUpdated(EditorRadialTiltShift radialTiltShift);

    void onLinearTiltShiftUpdated(EditorLinearTiltShift linearTiltShift);

    void onStraightenTransformChanged(Matrix transformMatrix);

    void updateDrawing(Paint paint, Path path);

    void updateDrawing(List<Drawing> drawings);

    void updateView();

    void onApplyChanges();

    void enableDrawingCache();
}