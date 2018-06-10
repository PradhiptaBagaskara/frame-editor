package com.masbon.worldcupeditor.core.model;

import android.graphics.Bitmap;

import com.masbon.worldcupeditor.core.enums.EditorTool;

public class EditorImage {
    private EditorTool mCommand;
    private Bitmap mBitmap;

    public EditorImage(EditorTool command, Bitmap bitmap) {
        mCommand = command;
        mBitmap = bitmap;
    }

    public EditorTool getCommand() {
        return mCommand;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

}