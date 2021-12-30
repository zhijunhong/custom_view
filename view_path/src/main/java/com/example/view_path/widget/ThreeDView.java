package com.example.view_path.widget;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author: zhijunhong
 * created on: 2021/12/30 10:10 AM
 * version: 1.0
 * description: 3D指南针View
 */
public class ThreeDView extends View {
    private Camera mCamera = new Camera();
    private Matrix mMatrix = new Matrix();

    private int mCenterX;
    private int mCenterY;

    private float mCanvasRotateX;
    private float mCanvasRotateY;

    private float mTouchX;
    private float mTouchY;

    private float mCanvasMaxRotateDegree = 50;
    private Paint mPaint;
    private int mAlpha = 200;
    private double alpha;
    private Path mPath;

    private int mBgColor;


    public ThreeDView(Context context) {
        this(context, null);
    }

    public ThreeDView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThreeDView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(30);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);

        mCanvasMaxRotateDegree = 20;
        mPath = new Path();

        mBgColor = Color.parseColor("#227BAE");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(mBgColor);
        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;

        canvas.rotate((float) alpha, mCenterX, mCenterY);
        alpha = Math.atan((mTouchX - mCenterX) / (mCenterY - mTouchY));
        alpha = Math.toDegrees(alpha);
        if (mTouchY > mCenterY) {
            alpha += 180;
        }

        rotateCanvas(canvas);

        mPaint.setColor(Color.WHITE);
        canvas.drawText("N", mCenterX, 150, mPaint);

        drawArc(canvas);
        drawCircle(canvas);
        drawPath(canvas);
    }

    private void drawPath(Canvas canvas) {
        canvas.save();

        mPath.moveTo(mCenterX, 293);
        mPath.lineTo(mCenterX - 30, mCenterY);
        mPath.lineTo(mCenterX, 2 * mCenterY - 293);
        mPath.lineTo(mCenterX + 30, mCenterY);
        mPath.lineTo(mCenterX, 293);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

        mPaint.setColor(Color.parseColor("#55227BAE"));
        canvas.drawCircle(mCenterX, mCenterY, 20, mPaint);

        canvas.restore();
    }

    private void drawCircle(Canvas canvas) {
        canvas.save();

        mPaint.setAlpha(255);
        canvas.drawCircle(mCenterX, 290, 10, mPaint);

        canvas.restore();
    }

    private void drawArc(Canvas canvas) {
        canvas.save();

        for (int i = 0; i < 120; i++) {
            mPaint.setAlpha(255 - (mAlpha * 1 / 120));
            canvas.drawLine(mCenterX, 250, mCenterX, 270, mPaint);
            canvas.rotate(3, mCenterX, mCenterY);
        }

        canvas.restore();
    }

    private void rotateCanvas(Canvas canvas) {
        canvas.save();
        mMatrix.reset();

        mCamera.save();
        mCamera.rotateX(mCanvasRotateX);
        mCamera.rotateY(mCanvasRotateY);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        mMatrix.preTranslate(-mCenterX, -mCenterY);
        mMatrix.postTranslate(mCenterX, mCenterY);

        canvas.concat(mMatrix);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        mTouchX = x;
        mTouchY = y;
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                rotateCanvasWhenMove(x, y);
                break;

            case MotionEvent.ACTION_MOVE:
                rotateCanvasWhenMove(x, y);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                mCanvasRotateX = 0;
                mCanvasRotateY = 0;
                invalidate();
                break;
        }
        return true;
    }

    private void rotateCanvasWhenMove(float x, float y) {
        float dx = x - mCenterX;
        float dy = y - mCenterY;

        float percentX = dx / mCenterX;
        float percentY = dy / mCenterY;

        if (percentX > 1f) {
            percentX = 1f;
        } else if (percentX < -1f) {
            percentX = -1f;
        }

        if (percentY > 1f) {
            percentY = 1f;
        } else if (percentY < -1f) {
            percentY = -1f;
        }

        mCanvasRotateX = mCanvasMaxRotateDegree * percentX;
        mCanvasRotateY = -(mCanvasMaxRotateDegree * percentY);
    }
}
