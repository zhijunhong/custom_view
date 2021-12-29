package com.example.view_path.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.view_path.R;

/**
 * author: zhijunhong
 * created on: 2021/12/4 2:21 PM
 * version: 1.0
 * description: 圆形水波纹加载View
 */
public class WaveLoadingView extends View {
    private Paint mPaint;
    private Paint mCirclePaint;
    private Paint mTextPaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Bitmap mBgBitmap;

    private Path mPath;

    private int mWidth;
    private int mHeight;
    private int x;
    private int y;

    private boolean isLeft;
    private float mPercent;
    private PorterDuffXfermode mMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    public WaveLoadingView(Context context) {
        this(context, null);
    }

    public WaveLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#8800ff66"));

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(Color.parseColor("#88dddddd"));

        mTextPaint = new Paint();
        mTextPaint.setStrokeWidth(10);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);

        mPath = new Path();
        mBitmap = Bitmap.createBitmap(550, 550, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.mmexport);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        y = mHeight;
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (x > 50) {
            isLeft = true;
        } else if (x < 0) {
            isLeft = false;
        }

        if (isLeft) {
            x = x - 1;
        } else {
            x = x + 1;
        }

        mPath.reset();

        y = (int) ((1 - mPercent / 100) * mHeight);
        mPath.moveTo(0, y);
        mPath.cubicTo(100 + x * 2, 50 + y, 100 + x * 2, y - 50, mWidth, y);
        mPath.lineTo(mWidth, mHeight);
        mPath.lineTo(0, mHeight);
        mPath.close();

        mBitmap.eraseColor(Color.parseColor("#00000000"));

        mCanvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mCirclePaint);
        mPaint.setXfermode(mMode);
        mCanvas.drawPath(mPath, mPaint);
        mPaint.setXfermode(null);

        canvas.drawBitmap(mBitmap, 0, 0, null);

        //绘制百分制文本
        String percent = mPercent + "";
        mTextPaint.setTextSize(80);
        float textLength = mTextPaint.measureText(percent);
        canvas.drawText(percent, mWidth / 2 - textLength / 2, mHeight / 2 + 15, mTextPaint);
        mTextPaint.setTextSize(40);
        canvas.drawText("%", mWidth / 2 + 70, mHeight / 2, mTextPaint);

        postInvalidateDelayed(10);
    }

    public void setPercent(int percent) {
        mPercent = percent;
    }
}
