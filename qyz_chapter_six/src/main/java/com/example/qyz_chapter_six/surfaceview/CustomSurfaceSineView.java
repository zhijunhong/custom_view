package com.example.qyz_chapter_six.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * surface view实现正弦曲线
 */
public class CustomSurfaceSineView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mHolder;                                      //surface holder

    private Canvas mCanvas;                                             //用于绘图的Canvas

    private boolean mIsDrawing;                                         //子线程标志

    private int x, y1, y2, y3, y4;
    private Path mPath;
    private Path mPath2;
    private Path mPath3;
    private Path mPath4;
    private Paint mPaint;

    public CustomSurfaceSineView(Context context) {
        this(context, null);
    }

    public CustomSurfaceSineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSurfaceSineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);

        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

        mPath = new Path();
        mPath2 = new Path();
        mPath3 = new Path();
        mPath4 = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
            x += 1;
            y1 = (int) (200 * Math.sin(x * 2 * Math.PI / 540) + 400);
            y2 = (int) (300 * Math.sin(x * 2 * Math.PI / 540) + 400);
            y3 = (int) (200 * Math.cos(x * 2 * Math.PI / 540 + Math.PI/2) + 400);
            y4 = (int) (300 * Math.cos(x * 2 * Math.PI / 540 + Math.PI/2) + 400);
            mPath.lineTo(x, y1);
            mPath2.lineTo(x, y2);
            mPath3.lineTo(x, y3);
            mPath4.lineTo(x, y4);
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            //SurfaceView背景
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
            mCanvas.drawPath(mPath2, mPaint);
            mCanvas.drawPath(mPath3, mPaint);
            mCanvas.drawPath(mPath4, mPaint);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
