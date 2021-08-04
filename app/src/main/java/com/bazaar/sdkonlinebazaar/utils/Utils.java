package com.bazaar.sdkonlinebazaar.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static boolean isValidString(String string) {
        boolean result = true;
        if ((string == null) || string.equalsIgnoreCase("")) {
            result = false;
        }
        return result;
    }

    public static boolean isValidList(List list) {
        boolean result = false;
        if (list != null && list.size() > 0) {
            result = true;
        }
        return result;
    }

    public static boolean isValidArrayList(ArrayList list) {
        boolean result = false;
        if (list != null && list.size() > 0) {
            result = true;
        }
        return result;
    }

    public static void showSnackBar(Activity activity, String message) {
        Snackbar snackBar = Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        snackBar.show();
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = AppCompatResources.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
//        use descriptor to show icon
//        Bitmap bitmap = Utils.getBitmapFromVectorDrawable(getContext(),R.drawable.ic_cam_marker_icon);
//        BitmapDescriptor descriptor =BitmapDescriptorFactory.fromBitmap(bitmap);

        return bitmap;
    }

}
