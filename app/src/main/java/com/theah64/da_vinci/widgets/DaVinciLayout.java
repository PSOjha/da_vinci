package com.theah64.da_vinci.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.theah64.da_vinci.R;

public class DaVinciLayout extends FrameLayout {

    private static final int ACCENT_STROKE_WIDTH = 8;

    // Clipping workers
    Path clipPath = new Path();
    private Rect rect = new Rect();
    private RectF rectF = new RectF();


    private static final String TAG = DaVinciLayout.class.getSimpleName();
    private int radiusPx;

    private DaVinciImageView lastView;


    public DaVinciLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public DaVinciLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        // Getting accent color to draw border
        int accentColor = ContextCompat.getColor(getContext(), R.color.colorAccent);

        // Getting width to calculate radius
        final DisplayMetrics dp = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(dp);

        int screenWidth = dp.widthPixels;
        this.radiusPx = screenWidth / 2;

        final GradientDrawable shapeDrawable = new GradientDrawable();
        shapeDrawable.setColor(Color.WHITE);
        shapeDrawable.setStroke(ACCENT_STROKE_WIDTH, accentColor);
        shapeDrawable.setCornerRadius(screenWidth / 2);

        setBackground(shapeDrawable);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.getClipBounds(rect);
        rectF.set(rect);
        clipPath.addRoundRect(rectF, radiusPx, radiusPx, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }

    public void addBitmap(Bitmap resource) {
        final DaVinciImageView iv = new DaVinciImageView(getContext());
        final ViewGroup.LayoutParams ivLayoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        iv.setLayoutParams(ivLayoutParams);
        iv.setImageBitmap(resource);
        iv.setCallback(new DaVinciImageView.Callback() {
            @Override
            public void onTouchTest(DaVinciImageView iv) {
                lastView = iv;
            }
        });

        addView(iv);
    }

    public void setActiveShapeRotation(int angle) {
        lastView.setRotation(angle);
    }

    public void setActiveShapeColor(int color) {
        lastView.setColorFilter(color);
    }

}
