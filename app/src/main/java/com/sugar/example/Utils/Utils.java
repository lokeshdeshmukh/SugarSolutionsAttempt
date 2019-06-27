package com.sugar.example.Utils;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Utils {

    public static String formatDate(String dateString) {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd,yyyy");
            return outputFormat.format(sdf.parse(dateString));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";

    }
    public void rorate_Clockwise(View view) {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 180f);
        rotate.setDuration(500);
        rotate.start();
    }

    public void rorate_AntiClockwise(View view) {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 180f, 0f);
        rotate.setDuration(500);
        rotate.start();
    }
    public void resizeWithAnimation(final View view, int duration, final int targetHeight) {
        final int initialHeight = view.getMeasuredHeight();
        final int distance = targetHeight - initialHeight;

        view.setVisibility(View.VISIBLE);

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1 && targetHeight == 0) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = (int) (initialHeight + distance * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(duration);
        view.startAnimation(a);
    }
}
