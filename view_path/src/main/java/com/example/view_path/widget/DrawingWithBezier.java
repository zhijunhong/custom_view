package com.example.view_path.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author: zhijunhong
 * created on: 2021/8/6 2:47 PM
 * version: 1.0
 * description:
 */
public class DrawingWithBezier extends View {
    private Paint mGesturePaint;
    private float mX;
    private float mY;
    private Path mPath = new Path();

    public DrawingWithBezier(Context context) {
        this(context, null);
    }

    public DrawingWithBezier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawingWithBezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mGesturePaint = new Paint();
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setStrokeWidth(5);
        mGesturePaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mGesturePaint);
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
        }

        invalidate();
        return true;
    }

    private void touchMove(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        final float previousX = mX;
        final float previousY = mY;
        final float deltaX = Math.abs(x - previousX);
        final float deltaY = Math.abs(y - previousY);

        //两点之间的距离大于等于3时，生成贝塞尔绘制曲线
        if (deltaX >= 3 || deltaY >= 3) {

            //设置白塞尔曲线的操作点为起点和终点的一半
            float oX = (x + previousX) / 2;
            float oY = (y + previousY) / 2;

            //二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
            mPath.quadTo(previousX, previousY, oX, oY);

            /*//对比lineTo的操作，发现贝塞尔曲线会顺滑很多
            mPath.lineTo( oX, oY);*/

            //第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
            mX = x;
            mY = y;
        }
    }

    private void touchDown(MotionEvent event) {
        mPath.reset();
        float x = event.getX();
        float y = event.getY();

        mX = x;
        mY = y;

        mPath.moveTo(x, y);
    }
}
