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

    private int LEVEL_CURRENT = 5;

    private float mLastCoordinateX;


    private float BG_LINE_HEIGHT; // progress bar background line height


    private int mThumbRadius = -1;

    private Paint mPaint;

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
        Log.d(TAG, "onDraw");
        int vWidth = getMeasuredWidth();
        int vHeight = getMeasuredHeight();
        if (mThumbRadius == -1) {
            mThumbRadius = vHeight >> 1;
        }
        // 1. draw the background
        mPaint.setColor(Color.GREEN);
        int left = mThumbRadius;
        float top = vHeight * 0.4f;
        int right = vWidth - mThumbRadius;
        float bottom = vHeight - top;
        canvas.drawRoundRect(left, top, right, bottom, 50, 50, mPaint);
        // 2. draw the thumb according the touch coordinate
        mPaint.setColor(Color.YELLOW);
        if (mLastCoordinateX == 0) {
            mLastCoordinateX = mThumbRadius;
        }
        canvas.drawCircle(mLastCoordinateX, mThumbRadius, mThumbRadius, mPaint);
    }

    @Override
    @SuppressWarnings("all")
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                float nowX = event.getX();

                if (nowX >= getMeasuredHeight() >> 1 && nowX <= getMeasuredWidth() - (getMeasuredHeight() >> 1)) {
                    mLastCoordinateX = nowX;
                }

                //Log.d(TAG, "ACTION_DOWN => X:" + mLastCoordinateX + " Y:" + mLastCoordinateY);
                invalidate();
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                float nowX = event.getX();

                if (nowX >= getMeasuredHeight() >> 1 && nowX <= getMeasuredWidth() - (getMeasuredHeight() >> 1)) {
                    mLastCoordinateX = nowX;
                }

                //Log.d(TAG, "ACTION_DOWN => X:" + mLastCoordinateX + " Y:" + mLastCoordinateY);
                invalidate();
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


}
