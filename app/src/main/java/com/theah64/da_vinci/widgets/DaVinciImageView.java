package com.theah64.da_vinci.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;


public class DaVinciImageView extends AppCompatImageView {

    private Callback callback;

    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

    public DaVinciImageView(Context context) {
        super(context);
        init();
    }

    public DaVinciImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    float dX, dY;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("Shifar", "Scale factor is " + mScaleFactor);
        int width = (int) (300 * mScaleFactor);
        int height = (int) (300 * mScaleFactor);
        setMeasuredDimension(width, height);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getPointerCount() > 1) {
            mScaleDetector.onTouchEvent(event);
            return true;
        } else {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    dX = getX() - event.getRawX();
                    dY = getY() - event.getRawY();
                    callback.onTouchTest(this);
                    break;

                case MotionEvent.ACTION_MOVE:
                    animate()
                            .x(event.getRawX() + dX)
                            .y(event.getRawY() + dY)
                            .setDuration(0)
                            .start();
                    break;
                default:
                    return super.onTouchEvent(event);
            }
        }

        return true;
    }

    public interface Callback {
        void onTouchTest(DaVinciImageView iv);
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            requestLayout();
            return true;
        }
    }

}
