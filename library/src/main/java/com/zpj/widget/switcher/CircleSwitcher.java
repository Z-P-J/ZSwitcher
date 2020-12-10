package com.zpj.widget.switcher;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class CircleSwitcher extends BaseSwitcher {

    public CircleSwitcher(Context context) {
        super(context);
    }

    public CircleSwitcher(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleSwitcher(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        super.initAttributes(context, attrs, defStyleAttr);
        defWidth = defHeight = Math.min(defWidth, defHeight);
    }

    @Override
    public void setIconProgress(float iconProgress) {
        if (this.iconProgress != iconProgress) {
            this.iconProgress = iconProgress;

            float iconOffset = Utils.lerp(0f, iconRadius - iconCollapsedWidth / 2, 1f - iconProgress);
            iconRect.left = (switcherRadius - iconCollapsedWidth / 2 - iconOffset) + shadowOffset;
            iconRect.right = (switcherRadius + iconCollapsedWidth / 2 + iconOffset) + shadowOffset;

            float clipOffset = Utils.lerp(0f, iconClipRadius, 1f - iconProgress);
            iconClipRect.set(
                    iconRect.centerX() - clipOffset,
                    iconRect.centerY() - clipOffset,
                    iconRect.centerX() + clipOffset,
                    iconRect.centerY() + clipOffset
            );
//            if (!Utils.isLollipopAndAbove()) generateShadow();
            postInvalidateOnAnimation();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h) {
        switcherRadius = (Math.min(w, h) / 2f) - shadowOffset;

        iconRadius = switcherRadius * 0.5f;
        iconClipRadius = iconRadius / 2.25f;
        iconCollapsedWidth = (iconRadius - iconClipRadius) * 1.1f;

        iconHeight = iconRadius * 2f;

        iconRect.set(
                (switcherRadius - iconCollapsedWidth / 2f) + shadowOffset,
                ((switcherRadius * 2f - iconHeight) / 2f) + shadowOffset / 2,
                (switcherRadius + iconCollapsedWidth / 2f) + shadowOffset,
                (switcherRadius * 2f - (switcherRadius * 2f - iconHeight) / 2f) + shadowOffset / 2
        );

//        if (!isChecked) {
////            iconRect.left = (switcherRadius - iconCollapsedWidth / 2f - (iconRadius - iconCollapsedWidth / 2f)) + shadowOffset;
////            iconRect.right = (switcherRadius + iconCollapsedWidth / 2f + (iconRadius - iconCollapsedWidth / 2f)) + shadowOffset;
//            iconRect.left = getWidth() - switcherRadius - iconCollapsedWidth / 2;
//            iconRect.right = getWidth() - switcherRadius + iconCollapsedWidth / 2;
//
//            iconClipRect.set(
//                    iconRect.centerX() - iconClipRadius,
//                    iconRect.centerY() - iconClipRadius,
//                    iconRect.centerX() + iconClipRadius,
//                    iconRect.centerY() + iconClipRadius
//            );
//        }
    }

    @Override
    protected SwitchOutline getSwitchOutline(int w, int h) {
        switcherRadius = (Math.min(w, h) / 2f) - shadowOffset;
        int d = (int) (switcherRadius * 2);
        return super.getSwitchOutline(d, d);
    }

    @Override
    protected void drawRect(Canvas canvas, @NonNull Paint paint) {
        canvas.drawCircle(switcherRadius + shadowOffset, switcherRadius + shadowOffset / 2,
                switcherRadius, paint);
    }

}