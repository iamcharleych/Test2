package com.chaplin.test1.ui.vehicles.fragments.map;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

class MapHelper {

    static Bitmap getBitmap(@NonNull Resources resources, @DrawableRes int drawableRes) {
        // prepare vector marker drawable
        VectorDrawableCompat drawable = VectorDrawableCompat.create(resources, drawableRes, null);

        if (drawable == null) {
            return null; // early exit
        }

        int markerWidth = drawable.getIntrinsicWidth();
        int markerHeight = drawable.getIntrinsicHeight();

        drawable.setBounds(0, 0, markerWidth, markerHeight);

        // create base bitmap to draw all the stuff
        Bitmap baseBitmap = Bitmap.createBitmap(markerWidth, markerHeight, Bitmap.Config.ARGB_8888);

        // create canvas
        Canvas canvas = new Canvas(baseBitmap);

        // draw marker
        drawable.draw(canvas);

        return baseBitmap;
    }
}
