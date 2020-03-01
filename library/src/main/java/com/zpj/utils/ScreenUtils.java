package com.zpj.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class ScreenUtils {

    public static float dp2px(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * density(context);
    }

    public static float px2dp(Context context, float px) {
        if (context == null) {
            return -1;
        }
        return px / density(context);
    }

    public static float density(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }


    public static int dp2pxInt(Context context, float dp) {
        return (int) (dp2px(context, dp) + 0.5f);
    }

    public static float px2dpInt(Context context, float px) {
        return (int) (px2dp(context, px) + 0.5f);
    }


    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        Activity activity;
        if (!(context instanceof Activity) && context instanceof ContextWrapper) {
            activity = (Activity) ((ContextWrapper) context).getBaseContext();
        } else {
            activity = (Activity) context;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    public static int[] getScreenPixelSize(Context context) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static int getAppInScreenheight(Context context) {
        return getScreenHeight(context) - getStatusBarHeight(context);
    }


}