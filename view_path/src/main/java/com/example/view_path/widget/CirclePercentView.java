package com.example.view_path.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * author: zhijunhong
 * created on: 2021/8/31 4:31 PM
 * version: 1.0
 * description:
 */
public class CirclePercentView extends View {
    private Paint mBigCirclePaint;
    private Paint mArcPaint;
    private Paint mSmallCirclePaint;
    private Paint mTextPaint;

    private float mRadius;
    private float mWidth;
    private float mHeight;
    private float mStripeWidth;

    private float mCenterX;
    private float mCenterY;

    private int mCurrentPercent;

    public CirclePercentView(Context context) {
        this(context, null);
    }

    public CirclePercentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRadius = dp2px(100f);
        mStripeWidth = dp2px(10f);

        mBigCirclePaint = new Paint();
        mBigCirclePaint.setStyle(Paint.Style.FILL);
        mBigCirclePaint.setAntiAlias(true);
        mBigCirclePaint.setColor(Color.BLUE);

        mArcPaint = new Paint();
        mArcPaint.setStyle(Paint.Style.FILL);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(Color.GREEN);

        mSmallCirclePaint = new Paint();
        mSmallCirclePaint.setStyle(Paint.Style.FILL);
        mSmallCirclePaint.setAntiAlias(true);
        mSmallCirclePaint.setColor(Color.BLUE);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(dp2px(14));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            mRadius = widthSize / 2;
            mWidth = widthSize;
            mHeight = heightSize;

            mCenterX = mWidth / 2;
            mCenterY = mHeight / 2;

        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            mWidth = mRadius * 2;
            mHeight = mRadius * 2;

            mCenterX = mRadius;
            mCenterY = mRadius;
        }

        setMeasuredDimension((int) mWidth, (int) mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mBigCirclePaint);

        RectF rectF = new RectF(0, mCenterY - mRadius, mWidth, mCenterY + mRadius);
        canvas.drawArc(rectF, -90, mCurrentPercent * 360 / 100, true, mArcPaint);

        canvas.drawCircle(mCenterX, mCenterY, mRadius - mStripeWidth, mSmallCirclePaint);

        String textPercent = String.valueOf(mCurrentPercent);
        float textLength = mTextPaint.measureText(textPercent);
        canvas.drawText(textPercent, mCenterX - textLength / 2, mCenterY, mTextPaint);
    }

    public float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public void setCurrentPercent(Integer animatedValue) {
        mCurrentPercent = animatedValue;
        invalidate();
    }
}
