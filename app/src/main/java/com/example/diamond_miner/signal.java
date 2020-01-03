package com.example.diamond_miner;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class signal {

    public static void vibrate(Context context, int duration) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(duration);
        }
    }


    public static void gameOverAnimate(ImageView img) {
        img.setScaleX(0);
        img.setScaleY(0);
        img.setRotation(0);
        img.animate()
                .rotation(360)
                .scaleX(1)
                .scaleY(1)
                .setDuration(2000)
                .setInterpolator(new BounceInterpolator())
                .start();
    }

    public static void diamondAnimate(ImageView img) {
        img.setScaleX(0);
        img.setScaleY(0);
        img.setRotation(0);
        img.animate()
                .rotation(360)
                .scaleX(1)
                .scaleY(1)
                .setDuration(1000)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();

    }




}
