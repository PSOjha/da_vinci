package com.theah64.da_vinci.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.theah64.da_vinci.utils.RotationGestureDetector;

public class DaVinciImageView extends AppCompatImageView implements View.OnTouchListener, RotationGestureDetector.OnRotationGestureListener {

    private static final String TAG = DaVinciImageView.class.getSimpleName();
    private RotationGestureDetector mRotationDetector;

    public DaVinciImageView(Context context) {
        super(context);
        init();
    }

    public DaVinciImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOnTouchListener(this);
        mRotationDetector = new RotationGestureDetector(this, this);
    }

    float dX, dY;

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                view.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
        }
        mRotationDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onRotation(RotationGestureDetector rotationDetector) {
        setRotation(rotationDetector.getAngle());
    }
}
