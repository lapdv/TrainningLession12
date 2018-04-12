package com.mobile.lapdv.trainninglession12.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.widget.LinearLayout;

import com.mobile.lapdv.trainninglession12.R;

/**
 * Created by lap on 10/04/2018.
 */

public class CircleLayoutUtils {
    public static void backgroundDayOfWeek(Context context, LinearLayout layout, boolean seleted) {
        ShapeDrawable footerBackground = new ShapeDrawable();
        int dimenColor = R.dimen.corners_color;
        float[] radii = new float[8];
        radii[0] = context.getResources().getDimension(dimenColor);
        radii[1] = context.getResources().getDimension(dimenColor);
        radii[2] = context.getResources().getDimension(dimenColor);
        radii[3] = context.getResources().getDimension(dimenColor);
        radii[4] = context.getResources().getDimension(dimenColor);
        radii[5] = context.getResources().getDimension(dimenColor);
        radii[6] = context.getResources().getDimension(dimenColor);
        radii[7] = context.getResources().getDimension(dimenColor);
        footerBackground.setShape(new RoundRectShape(radii, null, null));
        if (seleted) {
            footerBackground.getPaint().setColor(context.getResources().getColor(R.color.colorPrimary));
            layout.setBackgroundDrawable(footerBackground);
        } else {
            footerBackground.getPaint().setColor(context.getResources().getColor(R.color.colorPrimary));
            footerBackground.getPaint().setStrokeWidth(1);
            footerBackground.getPaint().setStyle(Paint.Style.STROKE);
            layout.setBackgroundDrawable(footerBackground);
        }
    }
}
