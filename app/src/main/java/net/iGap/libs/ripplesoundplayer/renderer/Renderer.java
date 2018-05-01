package net.iGap.libs.ripplesoundplayer.renderer;

import android.graphics.Canvas;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorInt;

/**
 * Created by jkimab on 2017. 8. 30..
 */

public abstract class Renderer {
    protected float[] points;
    protected double ampValue = 1.0;

    @CallSuper
    public void render(Canvas canvas, byte[] data, int width, int height) {
        if (points == null || points.length < data.length * 4) {
            points = new float[data.length * 4];
        }
    }

    public abstract void changeColor(@ColorInt int color);

    public abstract boolean isFFTDataRequired();

    public void setAmpValue(double ampValue) {
        this.ampValue = ampValue;
    }
}


