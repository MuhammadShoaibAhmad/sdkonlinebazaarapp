package com.bazaar.sdkonlinebazaar.ui.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Switch;

public class TintableSwitch extends Switch {
    private int mThumbColor = -1;

    public TintableSwitch(Context context) {
        super(context);
    }

    public TintableSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TintableSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setChecked(boolean checked) {
        super.setChecked(checked);
        changeColor(checked);
    }

    public void setThumbColor(int thumbColor) {
        this.mThumbColor = thumbColor;
    }

    private void changeColor(boolean isChecked) {
        if (this.mThumbColor != -1) {
            int thumbColor;
            int trackColor;
            if (isChecked) {
                thumbColor = this.mThumbColor;
                trackColor = thumbColor;
            } else {
                thumbColor = Color.argb(255, 236, 236, 236);
                trackColor = Color.argb(255, 0, 0, 0);
            }
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getThumbDrawable().setColorFilter(thumbColor, Mode.MULTIPLY);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getTrackDrawable().setColorFilter(trackColor, Mode.MULTIPLY);
                }
            } catch (NullPointerException e) {
             //   ThrowableExtension.printStackTrace(e);
            }
        }
    }
}
