package com.theah64.da_vinci.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class DaVinciCanvas extends View {

    private Bitmap bitmap;
    private static Paint circlePaint = new Paint();
    private static Paint bitmapPaint = new Paint();

    static {
        circlePaint.setColor(Color.WHITE);
        circlePaint.setDither(true);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL);

        bitmapPaint.setStyle(Paint.Style.FILL);
        bitmapPaint.setDither(true);
        bitmapPaint.setAntiAlias(true);
    }

    public DaVinciCanvas(Context context) {
        super(context);
    }

    public DaVinciCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int width = getWidth();
        final int height = getHeight();

        canvas.drawCircle(width / 2, height / 2, 500, circlePaint);

        if (bitmap != null) {
            final int bitmapHeight = bitmap.getHeight();
            final int bitmapWidth = bitmap.getWidth();
            final int bmpX = (width / 2) - (bitmapWidth / 2);
            final int bmpY = (height / 2) - (bitmapHeight / 2);
            canvas.save();
            canvas.rotate(90, width / 2, height / 2);
            canvas.drawBitmap(bitmap, bmpX, bmpY, bitmapPaint);
            canvas.restore();
        }
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.invalidate();
    }
}
