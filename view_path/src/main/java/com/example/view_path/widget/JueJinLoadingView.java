package com.example.view_path.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author: zhijunhong
 * created on: 2021/11/11 6:53 PM
 * version: 1.0
 * description: 掘金加载动画
 */

public class JueJinLoadingView extends View {
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Path mPath;
    private float mPathPercent;
    private float mCirclePercent;
    //竖线缩短的百分比
    private int mLineShrinkPercent = 0;
    private float mRisePercent = 0;
    //判断是不是正在draw
    private boolean isDrawing = false;
    private RectF mRectF;
    private boolean mCanStartDraw;
    private boolean isPathToLine;
    //标记上升是否完成
    private boolean isRiseDone = false;
    private float mLinePercent;


    public JueJinLoadingView(Context context) {
        this(context, null);
    }

    public JueJinLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JueJinLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#2EA4F2"));
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);

        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = 200;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = 200;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRectF = new RectF(5, 5, mWidth - 5, mHeight - 5);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2 - 5, mPaint);

        if (!mCanStartDraw) {
            isDrawing = true;
            mPaint.setColor(Color.WHITE);

            if (mLineShrinkPercent < 95) {
                float temp = (mWidth / 2 - mHeight / 4) * mLineShrinkPercent / 100;
                canvas.drawLine(mWidth / 2, mHeight / 4 + temp, mWidth / 2, mHeight * 3 / 4 - temp, mPaint);
                mLineShrinkPercent += 5;
            } else {
                isPathToLine = true;

                if (mPathPercent < 100) {
                    mPaint.setColor(Color.WHITE);
                    mPath.moveTo(mWidth / 4, mHeight / 2);
                    mPath.lineTo(mWidth / 2, mHeight * 3 / 4 - mPathPercent / 100 * mHeight / 4);
                    mPath.lineTo(mWidth * 3 / 4, mHeight / 2);
                    canvas.drawPath(mPath, mPaint);
                    mPathPercent += 5;

                    canvas.drawCircle(mWidth / 2, mHeight / 2, 2.5f, mPaint);
                } else {
                    //绘制把点上弹的直线
                    if (mRisePercent < 100) {
                        canvas.drawLine(mWidth / 4, mHeight / 2, mWidth * 3 / 4, mHeight / 2, mPaint);
                        canvas.drawCircle(mWidth / 2, mHeight / 2 - mHeight / 2 * mRisePercent / 100 + 5, 2.5f, mPaint);
                        mRisePercent += 5;
                    }else{
                        canvas.drawPoint(mWidth / 2, 5, mPaint);
                        isRiseDone = true;

                        if (mLinePercent < 100) {
                            mPath.moveTo(mWidth / 4, mHeight / 2);
                            mPath.lineTo(mWidth / 2, mHeight / 2 + mLinePercent / 100 * mHeight / 4);
                            mPath.lineTo(mWidth * 3 / 4, mHeight / 2 - mLinePercent / 100 * mHeight * 3 / 10);
                            canvas.drawPath(mPath, mPaint);
                            mLinePercent += 5;

                            //绘制圆弧
                            if (mCirclePercent < 100) {
                                canvas.drawArc(mRectF, 270, -mCirclePercent / 100 * 360, false, mPaint);
                                mCirclePercent += 5;
                            }
                        }else{
                            //绘制最终的path
                            mPath.moveTo(mWidth / 4, mHeight / 2);
                            mPath.lineTo(mWidth / 2, mHeight * 3 / 4);
                            mPath.lineTo(mWidth * 3 / 4, mHeight * 3 / 10);
                            canvas.drawPath(mPath, mPaint);

                            canvas.drawArc(mRectF, 270, -360, false, mPaint);

                            isDrawing = false;
                        }
                    }
                }
            }

            if (!isPathToLine) {
                mPath.moveTo(mWidth / 4, mHeight / 2);
                mPath.lineTo(mWidth / 2, mHeight * 3 / 4);
                mPath.lineTo(mWidth * 3 / 4, mHeight/2);
                canvas.drawPath(mPath, mPaint);
            }

        } else {
            mPaint.setColor(Color.WHITE);
            canvas.drawLine(mWidth / 2, mHeight / 4, mWidth / 2, mHeight * 3 / 4, mPaint);
            mPath.moveTo(mWidth / 4, mHeight / 2);
            mPath.lineTo(mWidth / 2, mHeight * 3 / 4);
            mPath.lineTo(mWidth * 3 / 4, mHeight / 2);
            canvas.drawPath(mPath, mPaint);
        }

        postInvalidateDelayed(100);
    }
}
