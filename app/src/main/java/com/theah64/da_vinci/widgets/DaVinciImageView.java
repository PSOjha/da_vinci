package com.theah64.da_vinci.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DaVinciImageView extends AppCompatImageView implements View.OnTouchListener {

    private Callback callback;

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
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    float dX, dY;

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                callback.onTouchTest((DaVinciImageView) view);
                break;

            case MotionEvent.ACTION_MOVE:
                view.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            default:
                return false;
        }
        return true;
    }

    public interface Callback {
        void onTouchTest(DaVinciImageView iv);
    }

}
