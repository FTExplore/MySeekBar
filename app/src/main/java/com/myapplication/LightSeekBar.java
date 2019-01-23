package com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class LightSeekBar extends View {

    private final String TAG = "LightSeekBar";

    private int LEVEL_MAX = 10;

    private int LEVEL_CURRENT = -1;

    private float mLastCoordinateX;

    private int mThumbRadius = 10;


    private Paint mPaint;

    public void setLEVEL_MAX(int LEVEL_MAX) {
        this.LEVEL_MAX = LEVEL_MAX;
    }

    public void setThumbRadius(int mThumbRadius) {
        this.mThumbRadius = mThumbRadius;
    }

    public interface OnProgerssBarListener {
        void onProgressChange(int i);
    }

    private OnProgerssBarListener mCallBack;

    public void setOnProgerssBarListener(OnProgerssBarListener mCallBack) {
        this.mCallBack = mCallBack;
    }

    public int getLEVEL_CURRENT() {
        return LEVEL_CURRENT;
    }

    public LightSeekBar(Context context) {
        super(context);
        init(context, null);
    }

    public LightSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, null);
    }

    public LightSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init(Context ctx, AttributeSet attributeSet) {
        Log.d(TAG, "init");
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

   /* public LightSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.d(TAG, "onDraw : " + getMeasuredWidth());
        float vWidth = getMeasuredWidth();
        int vHeight = getMeasuredHeight();

        // 1. draw the background
        mPaint.setColor(Color.GREEN);
        int left = mThumbRadius;
        float top = vHeight * 0.4f;
        float right = vWidth - mThumbRadius;
        float bottom = vHeight - top;
        canvas.drawRoundRect(left, top, right, bottom, 50, 50, mPaint);
        // 2. draw the thumb according the touch coordinate
        mPaint.setColor(Color.YELLOW);

        // check section the coordinate in
        float unit = right / (LEVEL_MAX - 1);
        float i = Math.round(mLastCoordinateX / unit);
        mLastCoordinateX = unit * i;

        if (mLastCoordinateX < left) {
            mLastCoordinateX = left;
        }

        if (mLastCoordinateX > right) {
            mLastCoordinateX = right;
        }

        canvas.drawCircle(mLastCoordinateX, vHeight >> 1, mThumbRadius, mPaint);

        // 3. callback
        if (LEVEL_CURRENT != i && i >= 0 && i <= (LEVEL_MAX - 1)) {
            LEVEL_CURRENT = (int) i;
            if (mCallBack != null) {
                mCallBack.onProgressChange(LEVEL_CURRENT);
            }
        }
    }

    @Override
    @SuppressWarnings("all")
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                updateLastCoordinate(event.getX());
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                updateLastCoordinate(event.getX());
            }
            break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                //Log.d(TAG, "ACTION_UP");
            }
            break;

        }


        return true;
    }

    private void updateLastCoordinate(float nowX) {
        mLastCoordinateX = nowX;
        //Log.d(TAG, "ACTION_DOWN => X:" + mLastCoordinateX);
        invalidate();
    }

}
