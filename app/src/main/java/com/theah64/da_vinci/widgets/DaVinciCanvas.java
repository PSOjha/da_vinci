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
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.theah64.da_vinci.R;

public class DaVinciCanvas extends View {

    private Bitmap bitmap;
    private final Paint whiteCirclePaint = new Paint();
    private final Paint bitmapPaint = new Paint();
    private final Paint orangeCirclePaint = new Paint();

    private float circlePadding;
    private float borderWidth;
    private float rotateDegree;
    private ColorFilter bitmapColorFilter = null;

    public DaVinciCanvas(Context context) {
        super(context);
        init();
    }

    /**
     * Initialized all the needed variable heres
     */
    private void init() {

        final int colorAccent = ContextCompat.getColor(getContext(), R.color.colorAccent);
        this.circlePadding = getResources().getDimension(R.dimen.circlePadding);
        this.borderWidth = getResources().getDimension(R.dimen.borderWidth);
        this.rotateDegree = 0;

        orangeCirclePaint.setColor(colorAccent);
        orangeCirclePaint.setDither(true);
        orangeCirclePaint.setAntiAlias(true);
        orangeCirclePaint.setStyle(Paint.Style.STROKE);
        orangeCirclePaint.setStrokeWidth(borderWidth);


        whiteCirclePaint.setColor(Color.WHITE);
        whiteCirclePaint.setAntiAlias(true);
        whiteCirclePaint.setDither(true);
        whiteCirclePaint.setStyle(Paint.Style.FILL);

        bitmapPaint.setStyle(Paint.Style.FILL);
    }

    public DaVinciCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Modes
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // Sizes
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = -1;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;

            case MeasureSpec.AT_MOST:
                width = Math.min(500, widthSize);
                break;

            case MeasureSpec.UNSPECIFIED:
                width = 500;
                break;
        }

        int height = -1;

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;

            case MeasureSpec.AT_MOST:
                height = Math.min(500, heightSize);
                break;

            case MeasureSpec.UNSPECIFIED:
                height = 500;
                break;
        }


        setMeasuredDimension(width, height);
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Canvas width and height
        final int cWidth = getWidth();
        final int cHeight = getHeight();

        // Circle position
        final int circleX = (cWidth / 2);
        final int circleY = (cHeight / 2);
        final float circleRadius = (cWidth / 2) - circlePadding;

        // Drawing circle
        canvas.drawCircle(circleX, circleY, circleRadius, whiteCirclePaint);
        canvas.drawCircle(circleX, circleY, circleRadius, orangeCirclePaint);

        if (bitmap != null) {

            if (bitmapColorFilter != null) {
                bitmapPaint.setColorFilter(bitmapColorFilter);
            }

            final int bitmapHeight = bitmap.getHeight();
            final int bitmapWidth = bitmap.getWidth();
            final int bmpX = (cWidth / 2) - (bitmapWidth / 2);
            final int bmpY = (cHeight / 2) - (bitmapHeight / 2);
            canvas.save();
            canvas.rotate(rotateDegree, circleX, circleY);
            canvas.drawBitmap(bitmap, bmpX, bmpY, bitmapPaint);
            canvas.restore();
        }
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.invalidate();
    }

    public void setBitmapRotation(int progress) {
        this.rotateDegree = progress;
        this.invalidate();
    }

    public void setBitmapColorFilter(int color) {
        this.bitmapColorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        this.invalidate();
    }
}
