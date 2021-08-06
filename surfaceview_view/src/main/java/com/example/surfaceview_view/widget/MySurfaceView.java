package com.example.surfaceview_view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * author: zhijunhong
 * created on: 2021/8/6 3:22 PM
 * version: 1.0
 * description:
 */
public class MySurfaceView extends SurfaceView {
    private Context mContext;
    private float mX;
    private float mY;
    private SurfaceHolder sfh;
    private Canvas mCanvas;
    private float mCurrentEndX;
    private float mCurrentEndY;

    private Paint mGesturePaint = new Paint();
    private Path mPath = new Path();
    private Rect mInvalidRect = new Rect();

    private boolean mIsDrawing;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        sfh = getHolder();
        initPaint();
    }

    private void initPaint() {
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setTextSize(5);
        mGesturePaint.setColor(Color.BLUE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                break;

            case MotionEvent.ACTION_MOVE:
                touchMove(event);
                break;

            case MotionEvent.ACTION_UP:
                touchUp();
                break;
        }
        return true;
    }

    private void touchUp() {
        mIsDrawing = false;
    }

    private void touchMove(MotionEvent event) {

        Rect areaToRefresh = null;

        float x = event.getX();
        float y = event.getY();

        final float previousX = mX;
        final float previousY = mY;

        final float deltaX = Math.abs(x - previousX);
        final float deltaY = Math.abs(y - previousY);

        if (deltaX >= 3 || deltaY >= 3) {
            areaToRefresh = mInvalidRect;
            areaToRefresh.set((int)mCurrentEndX, (int)mCurrentEndY, (int)mCurrentEndX, (int)mCurrentEndY);

            //设置贝塞尔曲线的操作点为起点和终点的一半
            float cX = mCurrentEndX = (x + previousX) / 2;
            float cY = mCurrentEndY = (y + previousY) / 2;

            mPath.quadTo(previousX, previousY, cX, cY);

            areaToRefresh.union((int) previousX, (int) previousY, (int) previousX, (int) previousY);
            areaToRefresh.union((int) cX, (int) cY , (int) cX, (int) cY);

            //第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
            mX = x;
            mY = y;
            drawCanvas();

        }

    }

    /**
     * 绘图
     */
    private void drawCanvas() {
        try {
            mCanvas = sfh.lockCanvas();
            if (mCanvas != null) {
                mCanvas.drawPath(mPath, mGesturePaint);
            }
        } catch (Exception e) {
            // do nothing
        } finally {
            sfh.unlockCanvasAndPost(mCanvas);
        }
    }

    private void touchDown(MotionEvent event) {
        mIsDrawing = true;
        mPath.reset();

        float x = event.getX();
        float y = event.getY();

        mX = x;
        mY = y;

        mPath.moveTo(x, y);

        mInvalidRect.set((int)x, (int)y, (int)x, (int)y);

        mCurrentEndX = x;
        mCurrentEndY = y;
    }
}
