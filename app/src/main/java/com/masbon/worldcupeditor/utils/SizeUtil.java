package com.masbon.worldcupeditor.utils;

import com.masbon.worldcupeditor.App;

public class SizeUtil {
    //private static final float GESTURE_THRESHOLD_DP = 16.0f;

    public static float dp2px(float dp) {
        return dp * getDensity();
    }

    public static float px2dp(float px) {
        return px / getDensity();
    }

    private static float getDensity() {
        return App.getAppComponent()
                .getContext()
                .getResources()
                .getDisplayMetrics()
                .density;
    }
}