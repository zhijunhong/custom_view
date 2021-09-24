package com.example.view_path.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * author: zhijunhong
 * created on: 2021/9/1 2:55 PM
 * version: 1.0
 * description: 简约的折线图
 */
public class SimpleLineChart extends View {
    private String mXAxis[];
    private String mYAxis[];
    private int mYAxisOffset = (int) dp2px(10);
    private Map<Integer, Integer> mPointMap;

    private float mWidth;
    private float mHeight;

    private Paint mTextPaint;
    private Paint mAxisPaint;
    private Paint mPointPaint;
    private Paint mLinePaint;
    private String mNoDataMsg;

    //Y轴字体的大小
    private float mYAxisFontSize = 24;

    private int mPointRadius = (int) dp2px(5);

    public SimpleLineChart(Context context) {
        this(context, null);
    }

    public SimpleLineChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleLineChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPointMap = new HashMap<>();
        mNoDataMsg = "No Data";

        mTextPaint = new Paint();
        mTextPaint.setTextSize(dp2px(18));
        mTextPaint.setColor(Color.RED);

        mAxisPaint = new Paint();
        mAxisPaint.setColor(Color.RED);
        mAxisPaint.setTextSize(mYAxisFontSize);
        mAxisPaint.setAntiAlias(true);

        mPointPaint = new Paint();
        mPointPaint.setColor(Color.BLUE);
        mPointPaint.setAntiAlias(true);

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLUE);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            throw new IllegalArgumentException("width must be EXACTLY,you should set like android:width=\"200dp\"");
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            throw new IllegalArgumentException("width must be EXACTLY,you should set like android:height=\"200dp\"");
        }

        setMeasuredDimension((int) mWidth, (int) mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mXAxis.length <= 0 || mYAxis.length <= 0) {
            throw new IllegalArgumentException("X or Y items is null!");
        }

        if (mPointMap == null || mPointMap.size() == 0) {
            int textLength = (int) mTextPaint.measureText(mNoDataMsg);
            canvas.drawText(mNoDataMsg, mWidth / 2 - textLength / 2, mHeight / 2, mTextPaint);
        }

        //画Y轴
        int[] yPoints = new int[mYAxis.length];
        int yInterval = (int) ((mHeight - mYAxisFontSize - 2) / (mYAxis.length));
        for (int i = 0; i < mYAxis.length; i++) {
            canvas.drawText(mYAxis[i], mYAxisOffset, mYAxisFontSize + i * yInterval, mAxisPaint);
            yPoints[i] = (int) (mYAxisFontSize + i * yInterval);
        }


        //画X轴
        int[] xPoints = new int[mXAxis.length];
        int startX = (int) mAxisPaint.measureText(mYAxis[1]);
        int startY = (int) (mYAxisFontSize + mYAxis.length * yInterval);
        int xOffset = 50;
        int xInterval = (int) ((mWidth - xOffset) / (mXAxis.length));
        for (int i = 0; i < mXAxis.length; i++) {
            canvas.drawText(mXAxis[i], startX + i * xInterval + xOffset, startY, mAxisPaint);
            xPoints[i] = (int) (i * xInterval + startX + mAxisPaint.measureText(mXAxis[i]) / 2 + xOffset + 10);
        }

        //画点
        for (int i = 0; i < mXAxis.length; i++) {
            if (mPointMap.get(i) == null) {
                throw new IllegalArgumentException("PointMap has incomplete data!");
            }

            canvas.drawCircle(xPoints[i], yPoints[mPointMap.get(i)], mPointRadius, mPointPaint);
            if (i > 0) {
                canvas.drawLine(xPoints[i - 1], yPoints[mPointMap.get(i - 1)], xPoints[i], yPoints[mPointMap.get(i)], mLinePaint);
            }
        }

        //画Y轴线
        int yOffset = 5;
        canvas.drawLine(mYAxisOffset + mAxisPaint.measureText(mYAxis[0]) + yOffset, 0, mYAxisOffset + mAxisPaint.measureText(mYAxis[0]) + yOffset, mYAxisFontSize + mYAxis.length * yInterval - mYAxisOffset, mAxisPaint);

        //画X轴线
        canvas.drawLine(mYAxisOffset + mAxisPaint.measureText(mYAxis[0]) + yOffset, mYAxisFontSize + mYAxis.length * yInterval - mYAxisOffset, startX + (mXAxis.length) * xInterval, mYAxisFontSize + mYAxis.length * yInterval-mYAxisOffset, mAxisPaint);
    }

    public float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public void setData(HashMap<Integer, Integer> data) {
        mPointMap = data;
        invalidate();
    }

    public void setYItems(String[] yItems) {
        mYAxis = yItems;
    }

    public void setXItems(String[] xItems) {
        mXAxis = xItems;
    }
}
